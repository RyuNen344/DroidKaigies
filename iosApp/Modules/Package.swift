// swift-tools-version: 5.10
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "Modules",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "App",
            targets: ["App"]
        ),
    ],
    targets: [
        .target(
            name: "App",
            swiftSettings: [
                .define("DEBUG", .when(configuration: .debug))
            ],
            plugins: [
                .plugin(name: "MultiplatformBuildPlugin")
            ]
        ),
        .plugin(
            name: "MultiplatformBuildPlugin",
            capability: .buildTool()
        )
    ]
)
