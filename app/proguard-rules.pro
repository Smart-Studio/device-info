#Guava
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-dontwarn sun.misc.Unsafe
-dontwarn java.lang.ClassValue
-dontwarn com.google.j2objc.annotations.Weak
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

#Retrolambda
-dontwarn java.lang.invoke.*

# Marshmallow removed Notification.setLatestEventInfo()
-dontwarn android.app.Notification

#Crashlytics
-keep class com.crashlytics.** { *; }
-keep class com.crashlytics.android.**
-keepattributes SourceFile,LineNumberTable,*Annotation*

-keep class android.support.v7.widget.RecyclerView { *; }