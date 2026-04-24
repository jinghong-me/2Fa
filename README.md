# 身份验证助手

安全高效的二步验证工具，支持 Google、GitHub、Steam 等多种平台。

## 功能特点

- 🔐 **扫码快速添加**：扫描标准 TOTP 二维码一键添加
- ⏱️ **实时动态生成**：每30秒自动刷新，带倒计时进度条
- 📋 **一键复制验证码**：点击验证码自动复制到剪贴板
- 📤 **导出二维码备份**：方便在其他设备上添加
- 🔒 **本地存储安全**：所有数据保存在本地，不上云
- 🎨 **简洁现代界面**：Material Design 风格，无广告

## 快速开始

1. 在网站开启二步验证（TOTP）
2. 打开身份验证助手，扫描二维码
3. 使用生成的验证码登录

## 下载

从 [Releases](https://github.com/jinghong-me/Authenticator/releases) 页面下载最新 APK。

## 项目结构

```
app/src/main/java/com/huahao/authenticator/
├── MainActivity.kt           # 主界面
├── ScanActivity.kt           # 扫码界面
├── ExportActivity.kt         # 导出二维码界面
├── QRCodeGenerator.kt        # 二维码生成工具
├── TotpGenerator.kt          # TOTP 验证码生成
├── AuthStore.kt              # 数据存储
└── models.kt                 # 数据模型
```

## 技术栈

- **语言**：Kotlin
- **UI**：Jetpack Compose + Material Design 3
- **二维码**：ML Kit Barcode Scanning
- **相机**：CameraX
- **数据存储**：DataStore
- **最低支持**：Android 8.0 (API 26)
- **目标版本**：Android 14 (API 34)

## 隐私安全

- 所有数据存储在本地
- 不上传到任何服务器
- 无第三方 SDK
- 无广告
- 无追踪

详见 [隐私政策](./docs/privacy.html)

## 许可证

MIT License

## 相关链接

- [官方网站](https://github.com/jinghong-me/Authenticator)
- [问题反馈](https://github.com/jinghong-me/Authenticator/issues)

---

© 2026 华昊科技有限公司