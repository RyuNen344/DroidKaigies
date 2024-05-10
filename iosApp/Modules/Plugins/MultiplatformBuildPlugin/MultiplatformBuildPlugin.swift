import PackagePlugin
import Foundation

@main
struct MultiplatformBuildPlugin: BuildToolPlugin {
    func createBuildCommands(
        context: PluginContext,
        target: Target
    ) async throws -> [Command] {
        let projectRoot = context.package.directory.appending(["..", ".."])
        let executable = projectRoot.appending(["gradlew"])
        
        print("hdoafheoahfoewhaof \(projectRoot)")
        print("hdoafheoahfoewhaof \(executable)")
        
        print(context.pluginWorkDirectory.appending([".gradle"]))
        
        @discardableResult
        func shell(_ args: String...) -> (String?, Int32) {
            let task = Process()
            task.environment = [
                "JAVA_HOME": "/Users/bunjiro/.sdkman/candidates/java/current",
                "GRADLE_USER_HOME": context.pluginWorkDirectory.appending([".gradle"]).string,
            ]
            task.launchPath = "/usr/bin/env"
            task.arguments = args
            let pipe = Pipe()
            task.standardOutput = pipe
            task.standardError = pipe
            task.launch()
            let data = pipe.fileHandleForReading.readDataToEndOfFile()
            let output = String(data: data, encoding: .utf8)
            task.waitUntilExit()
            return (output, task.terminationStatus)
        }
        
        
        shell("JAVA_HOME=/Users/bunjiro/.sdkman/candidates/java/current")
        
        let (result, _) = shell(executable.string, "tasks", "--stacktrace")
        print(result)
        
        return [
            .prebuildCommand(
                displayName: "MultiplatformBuildPlugin",
                executable: executable,
                arguments: [
                    "tasks",
                    "--stacktrace"
                ],
                environment: [
                    /* ⚠️ Must set JAVA_HOME absolute path! ⚠️ */
                    "JAVA_HOME": "/Users/bunjiro/.sdkman/candidates/java/current",
                    "GRADLE_USER_HOME": context.pluginWorkDirectory.appending([".gradle"]),
                ],
                outputFilesDirectory: context.pluginWorkDirectory
            )
        ]
    }
}


