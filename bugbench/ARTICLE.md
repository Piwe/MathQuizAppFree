# BugBench: Smarter Java & Kotlin Code Analysis for VS Code and Kiro

If you write Java or Kotlin for a living, you already know the routine: one linter flags a problem, your IDE underlines another, and you spend half your morning switching between linters, refactor menus, and a half-finished JUnit file. **BugBench** is a VS Code and Kiro extension that puts static analysis, auto-fix refactoring, and JUnit test scaffolding into a single in-editor experience, with a Model Context Protocol (MCP) server bundled in so AI agents can call the same tools you do.

In this article we'll tour what BugBench does, how to install it on Kiro or VS Code, how to use it day to day, and why it's worth a place in your Java toolbelt.

---

## What is BugBench?

BugBench is a JVM-powered language server packaged as an editor extension. It targets **Java and Kotlin** projects and bundles four things into one install:

1. **A static analysis engine**: PMD 7.5 plus about 60 BugBench rules for Java, and about 40 lightweight checks for Kotlin.
2. **An auto-fix refactoring engine**: 20 fixers covering 49 rules.
3. **A JUnit 5 test generator** that recognises Spring, JPA and Camel classes and copies your project's existing test style.
4. **An MCP server** so agentic IDEs like **Kiro**, Cursor, Claude Desktop, and Claude Code can call BugBench's analysis, refactoring and test-generation tools directly.

BugBench is published on the [Open VSX Registry](https://open-vsx.org/extension/bugbench/bugbench), which is where Kiro gets its extensions. VS Code users install the same `.vsix` from there.

---

## Features at a glance

### 1. In-editor static analysis

Open a `.java`, `.kt`, or `.kts` file and BugBench's language server analyses it and pushes diagnostics into the standard **Problems** panel. Findings use a familiar severity ladder (*Blocker / Critical / Major / Minor / Info*) and refresh as you edit and save. There's no project-wide scan to wait for.

- **Java:** PMD 7.5's 94 rules, plus about 60 BugBench rules covering security (hard-coded passwords, weak cryptography, insecure randomness and deserialization, XXE, sensitive data in logs), Spring beans and lifecycle, JPA/Lombok, REST controllers, Apache Camel routes, `Optional` misuse, dead stores, always-true conditions, infinite recursion and cognitive complexity.
- **Kotlin:** about 40 lightweight checks. The editor needs to recognise the files as Kotlin, which usually means having a Kotlin language extension installed.

### 2. One-click auto-fix refactoring

Wherever a finding is auto-fixable, BugBench offers a **lightbulb quick fix**. Its 20 fixers cover 49 rules, including:

- `try-with-resources` conversion
- Empty catch blocks and `InterruptedException` handling
- Unused imports and local variables
- Cognitive complexity reduction
- `Optional.get()` misuse
- String comparison, boolean and null-check simplification, for-each loops
- Spring lifecycle issues and constructor injection
- REST controller security
- Apache Camel route bugs
- 12 Kotlin rules

Java fixes adapt to the Java version your Maven or Gradle build declares. Applying a quick fix, or running **`BugBench: Apply auto-fixes to current file`**, applies every available fix in the file in one pass.

### 3. JUnit 5 test scaffolding

Above every top-level Java class you'll see a code lens: **Generate tests with BugBench**. BugBench works out what kind of class it is and picks a matching template:

- `@RestController` / `@Controller` → `@WebMvcTest` with `@MockBean` dependencies
- Repositories → `@DataJpaTest`
- Services, components, configurations, Camel `RouteBuilder`s and `Processor`s, and plain classes → Mockito-based tests

The scaffold also matches the tests you already have. BugBench reads the module's existing test sources and copies their assertion library (plain JUnit, AssertJ or Hamcrest), method naming (`testFoo`, `shouldFoo` or `givenX_whenY_thenZ`), and whether they use `@DisplayName` and `@Nested`. A team that writes `assertThat(...).isNotNull()` and `should…` names gets exactly that, with no configuration.

Generated tests never land on disk silently. The proposal opens in a side editor with a *Save as `<ClassName>Test.java`* action that places it in the matching `src/test/java` package. If that file already exists, you get *Show diff* instead, so you can decide what to merge.

### 4. Workspace report

Run **`BugBench: Scan workspace and open report`** and BugBench analyses every source file in your workspace (the first folder, in a multi-root workspace) and opens an HTML report: issue counts per severity and an expandable issue table for each file. It's handy for a code-health pass before a release.

### 5. Agent integration via MCP

This is the standout feature for anyone using **Kiro** or another agentic IDE. BugBench bundles an MCP server that exposes seven tools:

| Tool | What it does |
|---|---|
| `getJavaContext` | Returns the Java version, build system, test framework and JDK path |
| `findSymbol` | Describes the symbol at a line and column: kind, name, snippet, resolved JDK types |
| `listIssues` | Returns BugBench's findings for a file (up to 200), optionally filtered by severity |
| `listAvailableRefactors` | Lists every rule BugBench can auto-fix |
| `previewRefactor` | Returns the file with all auto-fixes applied, plus a change log, without writing |
| `applyRefactor` | Applies all auto-fixes to a file; dry-run unless `confirm=true` |
| `generateTests` | Produces a JUnit scaffold for a Java class; written only with `confirm=true` |

Tools that change files default to **dry-run**: the agent has to pass `confirm=true` for anything to change. The server is also sandboxed. It only touches files inside your workspace folders, checked after resolving symlinks, whatever root the agent asks for.

On Kiro, BugBench registers itself at user level automatically and offers once per workspace to register at workspace level, which Kiro needs for the agent to see the tools.

### 6. Learns which rules matter to your project (opt-in)

Every static analyser has rules your team simply doesn't care about. Turn on learning (BugBench asks once, on first run) and every issue gets one more quick fix: **BugBench: show *rule* less often in this project**.

- Each time you dismiss a rule more often than you fix it, it drops one severity level (at most two), and the diagnostic tells you why it was lowered.
- After three dismissals with few or no fixes, the rule is hidden in that project. The workspace report still counts what was hidden.
- **Blocker and Critical issues are never hidden** and drop at most one level, so a security finding can't be dismissed into silence.
- Preferences are per project: tuning out a rule in one repository doesn't affect another.

It matters most on Kiro. The agent's `listIssues` call returns the same adjusted severities (with `originalSeverity` alongside), so the agent works on what *your team* treats as important instead of re-raising the rules you keep dismissing. Fixes the agent applies are recorded separately and never count as you accepting a rule, because an agent that fixes everything it's offered would otherwise teach BugBench that every rule matters.

The learning is a simple, transparent count of your fixes and dismissals rather than an opaque model, and it's all local. See [Privacy](#privacy) for exactly what's stored.

---

## Installing the extension

### Requirements

- **JDK 17 or newer**. BugBench looks at `bugbench.javaHome`, then `JAVA_HOME`, `JDK_HOME`, the Red Hat Java extension's bundled JRE, and finally `java` on `PATH`. If it finds nothing suitable, it shows a notification with *Set Java Home* and *Download JDK 21*.
- **VS Code 1.85+**, or a **Kiro** build on that engine version or newer.
- Your project on a **local disk**. Virtual and remote-only workspaces aren't supported.

### On Kiro (from Open VSX)

1. Open Kiro.
2. Open the **Extensions** view (`Ctrl+Shift+X`).
3. Search for **BugBench** and click **Install**.

When BugBench activates for the first time in a workspace, you'll see a banner:

> *"Kiro needs BugBench registered at the workspace level for the agent to see its tools."*

Click **Register here**, then **fully restart Kiro** (close all windows and reopen) so the agent re-reads the MCP config. To check it worked, ask Kiro's agent:

> *"Use getJavaContext on this workspace."*

A JSON response with `javaVersion`, `buildSystem` and friends means the agent can see BugBench's tools.

If you dismissed the prompt, run `Ctrl+Shift+P` → **`BugBench: Register MCP server in current workspace (Kiro)`**.

### On VS Code (from a `.vsix`)

1. Open [BugBench on Open VSX](https://open-vsx.org/extension/bugbench/bugbench) and download the `.vsix`.
2. In VS Code, open the Command Palette (`Ctrl+Shift+P`).
3. Run **`Extensions: Install from VSIX...`** and pick the file.

Or from a terminal: `code --install-extension bugbench.bugbench-<version>.vsix`.

---

## Using the extension day to day

Once installed, there's nothing special to do: open a Java or Kotlin project and diagnostics appear within a few seconds.

### See and fix issues

1. Open a `.java` or `.kt` file.
2. Watch the gutter and the **Problems** panel populate.
3. Put the cursor on an underlined region and open the lightbulb (`Ctrl+.`).
4. Pick a BugBench fix to apply all available fixes in the file. With learning on, you can instead pick *show this rule less often in this project* if it's noise for your codebase.

### Refactor a whole file in one go

```
BugBench: Apply auto-fixes to current file
```

Every applicable fixer runs as a single edit.

### Generate JUnit scaffolding

Either click the **Generate tests with BugBench** code lens above a class, or run **`BugBench: Generate JUnit test scaffold`** from the Command Palette. The proposal opens in a side editor with a *Save as `<ClassName>Test.java`* action.

### Run a workspace audit

```
BugBench: Scan workspace and open report
```

Set `bugbench.includeTests` to `true` to include `src/test/` sources.

### Use the MCP server from other hosts

To use BugBench from Cursor, Claude Desktop, or Claude Code:

```
BugBench: Install MCP server for current host (copy JSON snippet)
```

This copies a registration block to your clipboard, ready to paste into the host's MCP config.

### Full command list

All commands are under the **BugBench:** prefix in the Command Palette:

- `Apply auto-fixes to current file`
- `Generate JUnit test scaffold`
- `Scan workspace and open report`
- `Restart language and MCP servers`
- `Show server logs`
- `Install MCP server for current host (copy JSON snippet)`
- `Register MCP server in current workspace (Kiro)`
- `Toggle ML data collection`
- `Export collected ML data (JSON)`
- `Delete all collected ML data`

---

## Configuration

BugBench's settings live under the **BugBench** category:

| Setting | Default | Purpose |
|---|---|---|
| `bugbench.javaHome` | `""` | JDK 17+ home; overrides `JAVA_HOME` / `JDK_HOME`. |
| `bugbench.lsp.enabled` | `true` | Run the language server (turn off for MCP-only use). |
| `bugbench.includeTests` | `false` | Include `src/test/` sources in workspace scans. |
| `bugbench.mcp.autoRegister` | `true` | Write the MCP registration on activation. |
| `bugbench.mcp.configPath` | `""` | Override the host's MCP config path (default: `~/.kiro/settings/mcp.json`). |
| `bugbench.ml.collectionEnabled` | `null` | Learn rule preferences per project; `null` asks on first run. |
| `bugbench.ml.dataDir` | `""` | Where learning data is kept (default: `~/.bugbench/ml`). |

---

## Privacy

BugBench is local-first:

- Analysis, fixes and test generation run **entirely on your machine**. BugBench makes no network calls of its own.
- Learning is **off until you say yes** to a one-time prompt. When on, it appends to `~/.bugbench/ml/events.tsv`: a timestamp, whether the event came from you or the agent, the rule ID (or tool name), the action, and a hash of the project path. **No source code, file names or paths are stored.**
- Matching your test style reads your test files locally and stores nothing, so it needs no opt-in.
- VS Code's `telemetry.telemetryLevel` setting is a hard kill-switch: when it's `off`, learning is paused.
- *Export collected ML data (JSON)* gives you every recorded event; *Delete all collected ML data* erases them and resets every learned severity.

---

## Why use BugBench?

- **One install covers analysis, fixes and tests.** These usually come from separate extensions or command-line tools. BugBench puts them behind a single language server with one severity scale and one report.
- **Agent-ready out of the box.** On Kiro (or any MCP-capable host), the engine behind your editor is available to your AI agent. Instead of you relaying between the agent's suggestions and the linter, the agent can call `listIssues`, preview fixes, and apply them with your confirmation.
- **Safe by design.** Tools that change files are dry-run by default and sandboxed to your workspace. Generated tests open in a side editor and never overwrite files silently.
- **JVM-native depth.** Analysis and fixes understand Spring, JPA, REST controllers and Camel routes, not just generic Java smells.
- **It adapts to your team.** Generated tests follow your existing style, and opt-in learning tones down the rules you keep dismissing, for you and your agent alike.
- **Local-first privacy.** No source code ever leaves your machine.
- **Resilient.** If a BugBench server crashes, it restarts automatically (up to five times in three minutes before notifying you), and *Show server logs* is one command away.

---

## Wrapping up

BugBench takes the well-trodden Java/Kotlin static analysis stack and reshapes it into something that feels native to a modern, agent-aware editor. If you live in VS Code or Kiro and write JVM code, it's worth a fifteen-minute test drive: install it, open a real project, run *Scan workspace and open report*, and see what your codebase looks like through BugBench's lens.

Get it on **[Open VSX](https://open-vsx.org/extension/bugbench/bugbench)**, and let your editor and your agent do more of the boring work for you.
