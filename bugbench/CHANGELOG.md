# Changelog

All notable changes to **BugBench** are listed here. Versions follow
the calendar-version scheme `<year>.<minor>.<patch>` shared with the
upstream multi-module project.

## 2026.3.0 — local learning, test-style matching & fixes (2026-10-20)

### README logo on Open VSX
- The README logo was broken on the Open VSX listing. `vsce` rewrites
  relative image paths to `github.com/<repo>/raw/...`, which pointed at
  the old placeholder repo and would 404 against the private repo too.
  The logo now loads from the icon Open VSX already hosts.

### Test generation follows your project's conventions
- `Generate JUnit test scaffold` and the MCP `generateTests` tool now
  read the module's existing test sources (`src/test*`, `test/`) and
  match their assertion library (JUnit / AssertJ / Hamcrest), method
  naming (`test…` / `should…` / `given…When…Then…`), and `@DisplayName`
  and `@Nested` usage. With no tests to learn from, the defaults
  are unchanged. The scan is local, read-only and bounded, and is
  never stored. On MCP it stays inside the sandboxed workspace root.

### Learns which rules matter in each project (opt-in, local)
- New opt-in learning, entirely on your machine:
  - Opted-in users get a *show <rule> less often in this project* quick
    fix on every issue. Rules dismissed more often than fixed drop one
    severity level per net dismissal (max two); after three lopsided
    dismissals they are hidden in that project. Blocker/Critical issues
    drop at most one level and are never hidden. Lowered diagnostics
    explain why; the workspace report counts hidden issues.
  - MCP `listIssues` returns the adjusted severity, `originalSeverity`
    and `hiddenByProjectPreferences`, so Kiro's agent prioritises the
    same way you do. Agent-applied fixes are recorded on a separate
    channel and never count as developer acceptance.
  - Events go to `~/.bugbench/ml/events.tsv`: rule ID, action, source
    and a hashed project ID — no code, file names or paths. Export now
    includes every event; Delete resets learned severities.
  - The extension shares consent with both servers via
    `~/.bugbench/ml-consent.properties`, so host-launched MCP servers
    (Kiro) honour it too. Missing file or learning off = stock
    behaviour. VS Code's `telemetry.telemetryLevel=off` pauses learning.
  - A first-run prompt asks once; a status-bar item shows whether
    learning is on or paused.
- `bugbench.ml.collectionEnabled` and `bugbench.ml.dataDir` are
  application-scoped, so a workspace's settings cannot opt you in.
- Removed the `bugbench.ml.syncEnabled` setting, which had no effect.

### Fixes
- The *Generate tests with BugBench* code lens now generates tests for
  the class it sits above; it previously always used the file's first
  class.
- MCP registration no longer overwrites an `mcp.json` it cannot parse
  (which wiped the other servers in it). It leaves the file untouched
  and shows a warning instead.
- Analysis waits for a 300 ms pause in typing instead of running on
  every keystroke; opening and saving still analyse immediately.
- SpotBugs is skipped up front when its command-line tool is not
  installed, instead of compiling every file with javac first. The
  check is cached and repeated every five minutes.
- The extension no longer starts an MCP server process of its own that
  nothing used; MCP hosts such as Kiro launch their own. The restart
  command is now *BugBench: Restart language server*.

### Documentation
- README and listing now describe only what the extension does today:
  corrected rule and fixer counts, MCP tool behaviour, and install
  steps (VS Code installs the `.vsix` from Open VSX).
- The extension no longer claims limited support for virtual
  workspaces; it needs files on a local disk.
- The listing's *Homepage* and the first-run prompt's *Learn more*
  link now open the public documentation.

## 2026.2.2 — listing metadata fix (2026-08-06)

Metadata-only release. No code changes; the bundled language and MCP
servers are byte-identical to 2026.2.1.

- `repository`, `bugs` and `homepage` in `package.json` pointed at a
  placeholder `https://github.com/REPLACE-ME/bugbench-vscode` URL, so the
  *Repository* / *Issues* / *Homepage* links on the Open VSX listing
  were dead. They now point at
  <https://github.com/Piwe/bugbench>. That repository is currently
  private, so the links only resolve for people with access.

## 2026.2.1 — security & thread-safety hardening (2026-07-14)

Hardening pass over the agent-facing MCP surface and the shared analysis
engine. No user-visible feature changes.

### MCP filesystem sandbox
- Tool paths are now confined to workspace roots supplied by the
  extension (`--allowed-root`, repeatable; `BUGBENCH_ALLOWED_ROOTS` env
  or the process working directory as fallback), not to the
  agent-supplied `workspaceRoot`. `workspaceRoot` itself is validated
  against the allowed roots. Closes a hole where an agent could set
  `workspaceRoot=/` and read or overwrite any reachable file.
- Containment checks resolve real (symlink-resolved) paths, so a symlink
  inside a root can no longer escape it.
- The workspace-level Kiro registration writes `--allowed-root <folder>`
  into `.kiro/settings/mcp.json`, so host-launched servers are bound to
  the workspace too.

### Denial-of-service limits
- File reads are capped at 16 MiB and JSON-RPC request lines at 8 MiB to
  protect the long-lived server from OOM on hostile or malformed input.

### Information disclosure
- Unexpected tool/handler errors are logged server-side and returned to
  the agent as generic messages instead of raw exception text.
- The scan report webview gained a restrictive Content-Security-Policy
  (scripts already disabled).

### Thread safety
- Analysis is serialized per workspace, guarding the non-thread-safe
  `CoreAnalysisOrchestrator` / JavaParser against concurrent LSP requests.

## 2026.2.0 — initial public release

First public release, on Open VSX. Brings the BugBench engine — previously
only available as a JetBrains plugin — to VS Code and Kiro.

### Editor surface (LSP)
- Diagnostics on `didOpen` / `didChange` / `didSave` for `.java`,
  `.kt`, and `.kts` files. Severity-aware mapping into VS Code's
  Problems panel.
- Quick-fix code actions for every auto-fixable issue intersecting
  the cursor's range. 20 fixers covering 49 rules, including
  `try-with-resources`, empty catch blocks, unused imports/variables,
  `Optional` misuse, Spring lifecycle, REST controller security, Camel
  route bugs, and cognitive complexity.
- Code-lens above every top-level Java class: *▸ Generate tests with
  BugBench*.
- Workspace-wide HTML report via *BugBench: Scan workspace and open
  report*: severity counts and per-file expandable issue tables.

### Agent surface (MCP)
- Bundled MCP server with seven tools: `getJavaContext`,
  `findSymbol`, `listIssues`, `listAvailableRefactors`,
  `applyRefactor`, `previewRefactor`, `generateTests`.
- Auto-registration into Kiro's MCP config at user level, with a
  one-click prompt to also register at workspace level (Kiro
  requires this for the agent to see the tools).
- Mutating tools default to dry-run; agent must pass
  `confirm=true` to write. Path arguments are validated against the
  declared `workspaceRoot`.

### Test generation UX
- Generated test opens in a side editor; never written to disk
  silently. Notification offers a one-click *Save as <ClassName>Test.java*
  using the conventional Maven/Gradle test path. If the file already
  exists, the action becomes *Show diff vs <ClassName>Test.java* and
  opens a diff editor against the proposal.

### Infrastructure
- Extension activates on `onLanguage:java` / `onLanguage:kotlin`.
- JDK discovery in five-step priority: `bugbench.javaHome` setting →
  `JAVA_HOME` → `JDK_HOME` → bundled `redhat.java` JREs → `java` on
  PATH.
- LSP and MCP child processes have crash supervision (cap of 5
  restarts in 3 minutes; surfaces a notification on the cap).
- `BugBench: Restart language and MCP servers` and
  `BugBench: Show server logs` for diagnostics.
