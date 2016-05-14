#Guava
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-dontwarn sun.misc.Unsafe
-dontwarn java.lang.ClassValue

#Retrolambda
-dontwarn java.lang.invoke.*

#Leak canary
#-keep class org.eclipse.mat.** { *; }
#-keep class com.squareup.leakcanary.** { *; }
# Marshmallow removed Notification.setLatestEventInfo()
-dontwarn android.app.Notification

#Crashlytics
-keep class com.crashlytics.** { *; }
-keep class com.crashlytics.android.**
-keepattributes SourceFile,LineNumberTable,*Annotation*

-keep class android.support.v7.widget.RecyclerView { *; }