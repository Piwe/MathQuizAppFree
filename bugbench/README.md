# BugBench

Static analysis, auto-fix refactoring, and JUnit test scaffolding for
**Java and Kotlin**, designed for VS Code and **Kiro**. Powered by a
JVM language server, with an MCP server bundled alongside so Kiro's
agent can call BugBench's tools directly during reasoning.

<!-- Absolute Open VSX URL on purpose: vsce rewrites relative image paths to
     github.com/<repo>/raw/..., which 404s while the repo is private. -->
![BugBench logo](https://open-vsx.org/api/bugbench/bugbench/latest/file/bugbench.png)

## Features

### In-editor analysis
- **Java:** PMD 7.5 (94 rules) plus about 60 BugBench rules covering
  security (hard-coded passwords, weak cryptography, insecure
  randomness and deserialization, XXE, sensitive data in logs),
  Spring beans and lifecycle, JPA/Lombok, REST controllers, Apache
  Camel routes, `Optional` misuse, dead stores, always-true
  conditions, infinite recursion, and cognitive complexity.
- **Kotlin** (`.kt`, `.kts`): about 40 lightweight BugBench checks.
  Files must be recognised as Kotlin by the editor, which usually means
  having a Kotlin language extension installed.
- Diagnostics refresh as you open, edit and save, and appear in the
  Problems panel with BugBench's severity ladder
  (Blocker / Critical / Major / Minor / Info).

### Auto-fix refactoring
- 20 fixers covering 49 rules (37 Java, 12 Kotlin), including
  try-with-resources, empty catch blocks, unused imports and local
  variables, cognitive complexity, `Optional.get()` misuse,
  `InterruptedException` handling, string comparison, boolean and null
  check simplification, for-each loops, Spring lifecycle and
  constructor injection, REST controller security, and Camel routes.
- Fixes for Java adapt to your project's Java version, read from
  Maven/Gradle build files.
- A lightbulb quick fix appears on every auto-fixable issue. Applying
  it, or running `BugBench: Apply auto-fixes to current file`, applies
  **every** available fix in the file in one pass.

### Test scaffolding (Java)
- A code lens above each top-level Java class:
  *Generate tests with BugBench*.
- Detects the class type and picks a matching JUnit 5 template:
  `@RestController`/`@Controller` (`@WebMvcTest` + `@MockBean`),
  repositories (`@DataJpaTest`), services, components,
  configurations, Camel `RouteBuilder`s and `Processor`s, and plain
  classes (Mockito).
- Matches your existing tests: assertion library (JUnit / AssertJ /
  Hamcrest), method naming (`test…` / `should…` / `given…When…Then…`),
  and `@DisplayName` / `@Nested` usage are read from the module's
  current test sources. Nothing is stored.
- The proposal opens in a side editor and is never written silently.
  *Save as `<Class>Test.java`* puts it in the `src/test/java` mirror of
  the source's package; if that file exists, *Show diff* opens a diff
  instead.

### Workspace report
- `BugBench: Scan workspace and open report` analyses every source in
  the first workspace folder and opens an HTML report: issue counts
  per severity and an expandable issue table per file.

### Learns which rules matter to you (opt-in)
- With learning on, every issue also gets a
  *BugBench: show &lt;rule&gt; less often in this project* quick fix.
  A rule you dismiss more than you fix drops one severity level per
  net dismissal (at most two), and after three lopsided dismissals it
  is hidden in that project. Blocker/Critical issues drop at most one
  level and are never hidden. Lowered diagnostics say so in their
  message; the workspace report counts hidden issues.
- The same preferences reach the agent: `listIssues` returns the
  adjusted severity plus `originalSeverity`, and reports
  `hiddenByProjectPreferences`. Fixes the agent applies are recorded
  separately and never count as you accepting a rule.
- A status-bar item shows whether learning is on or paused.

### Agent integration via MCP
- Bundles a Model Context Protocol server. On Kiro, BugBench
  registers itself in `~/.kiro/settings/mcp.json` on activation and
  offers once per workspace to register in `.kiro/settings/mcp.json`
  too (Kiro needs the workspace entry for the agent to see the tools).
- Tools the agent can call:

  | Tool | What it does |
  |---|---|
  | `getJavaContext` | Java version, build system, test framework, JDK path |
  | `findSymbol` | What the symbol at a line/column is (kind, name, snippet, resolved JDK types) |
  | `listIssues` | The file's findings (up to 200), with an optional minimum severity |
  | `listAvailableRefactors` | Every rule BugBench can auto-fix |
  | `previewRefactor` | The file with all auto-fixes applied, plus a change log; never writes |
  | `applyRefactor` | Applies all auto-fixes to the file; dry-run unless `confirm=true` |
  | `generateTests` | A JUnit scaffold for a Java file; written only with `confirm=true` and `outputPath` |

- `BugBench: Install MCP server for current host (copy JSON snippet)`
  copies a ready-made config entry for Cursor, Claude Desktop or
  Claude Code.

## Requirements

- **JDK 17 or newer**, found via `bugbench.javaHome`, `JAVA_HOME`,
  `JDK_HOME`, the Red Hat Java extension's bundled JRE, or `java` on
  `PATH`. If none is found, BugBench shows a notification with
  *Set Java Home* and *Download JDK 21* and stays inactive.
- VS Code 1.85+ or a Kiro version built on that engine.
- Files on a local disk. Virtual and remote-only workspaces are not
  supported.

## Install

- **Kiro:** open the Extensions view, search for **BugBench**, and
  install it (Kiro uses the Open VSX Registry).
- **VS Code:** download the `.vsix` from
  [BugBench on Open VSX](https://open-vsx.org/extension/bugbench/bugbench)
  and run `Extensions: Install from VSIX...` (or
  `code --install-extension bugbench.bugbench-<version>.vsix`).

Then open a Java or Kotlin project; diagnostics appear within a few
seconds. On Kiro, click *Register here* when prompted, then fully
restart Kiro and ask the agent: *"Use getJavaContext on this
workspace."*

## Configuration

| Setting | Default | Purpose |
|---|---|---|
| `bugbench.javaHome` | `""` | JDK 17+ home; overrides `JAVA_HOME` / `JDK_HOME`. |
| `bugbench.lsp.enabled` | `true` | Run the language server (turn off for MCP-only). |
| `bugbench.includeTests` | `false` | Include `src/test/` sources in workspace scans. |
| `bugbench.mcp.autoRegister` | `true` | Write the MCP registration on activation. |
| `bugbench.mcp.configPath` | `""` | Override the host's MCP config path. Default: `~/.kiro/settings/mcp.json`. |
| `bugbench.ml.collectionEnabled` | `null` | Learn rule preferences per project; `null` asks on first run. User-level only. |
| `bugbench.ml.dataDir` | `""` | Where learning data is kept. Default: `~/.bugbench/ml`. User-level only. |

## Commands

All under the **BugBench:** prefix in the Command Palette:

- `Apply auto-fixes to current file`
- `Generate JUnit test scaffold`
- `Scan workspace and open report`
- `Restart language server`
- `Show server logs`
- `Install MCP server for current host (copy JSON snippet)`
- `Register MCP server in current workspace (Kiro)`
- `Toggle ML data collection`
- `Export collected ML data (JSON)`
- `Delete all collected ML data`

## Privacy

- Analysis, fixes and test generation run **entirely on your
  machine**. BugBench makes no network calls of its own.
- Learning is **opt-in** and asks once on first run. When on, it
  appends events to `~/.bugbench/ml/events.tsv`: timestamp, source
  (editor or agent), event type, rule ID or tool name, action, and a
  hashed project ID. **No source code, file names or paths are
  recorded.**
- `Export collected ML data (JSON)` writes every recorded event;
  `Delete all collected ML data` removes them and resets learned
  severities.
- The extension passes your choice to the language and MCP servers via
  `~/.bugbench/ml-consent.properties`. With no such file, or with
  learning off, both servers behave exactly as stock.
- VS Code's `telemetry.telemetryLevel` setting is a hard kill-switch:
  when it is `off`, learning is paused.
- Matching your test style reads your test files locally and stores
  nothing, so it needs no opt-in.

## Security

The MCP server is an agent-facing surface, so its filesystem access is
sandboxed:

- **Workspace-bound paths.** The extension launches the server with
  `--allowed-root` set to your workspace folders (otherwise
  `BUGBENCH_ALLOWED_ROOTS`, then the working directory, apply). Every
  path the agent passes, including `workspaceRoot`, is checked against
  those roots after resolving symlinks.
- **Write safety.** Tools that change files default to dry-run; a file
  changes only when the agent passes `confirm=true`.
- **Resource limits.** File reads are capped at 16 MiB and JSON-RPC
  messages at 8 million characters.
- **No detail leakage.** Unexpected errors are logged locally; the
  agent gets a generic message without internal paths or stack traces.
- **Locked-down report.** The scan report webview runs with scripts
  disabled and a restrictive Content-Security-Policy.

## License

Proprietary. See [LICENSE](https://github.com/Piwe/MathQuizAppFree/blob/master/bugbench/LICENSE) for terms and contact details.
