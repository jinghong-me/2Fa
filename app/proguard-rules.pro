# ProGuard 规则文件

# 保留数据模型类
-keep class com.huahao.authenticator.** { *; }

# 保留 Compose
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**
