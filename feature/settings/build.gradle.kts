/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.android.build.api.dsl.ManagedVirtualDevice

plugins {
    id("nowinandroid.android.feature")
    id("nowinandroid.android.library.compose")
    id("nowinandroid.android.library.jacoco")

    id("io.gitlab.arturbosch.detekt").version("1.22.0")
    id("es.horm.easyadldetektplugin.gradleplugin").version("0.0.1")
}

android {
    namespace = "com.google.samples.apps.nowinandroid.feature.settings"
}

dependencies {
    detektPlugins("es.horm.easyadldetektplugin:easyAdlDetektPlugin:0.0.1")
}

easyAdl {
    archDescriptionPath = "C:\\Users\\thoma\\AndroidStudioProjects\\nowinandroid\\archDescription.eadl"
}

detekt {
    toolVersion = "1.22.0"
    config = files("../../config/detekt/detekt.yml")
    buildUponDefaultConfig = true
}

tasks {
    withType<io.gitlab.arturbosch.detekt.Detekt> {
        reports {
            custom {
                reportId = "ArchReport"
                // This tells detekt, where it should write the report to,
                // you have to specify this file in the gitlab pipeline config.
                outputLocation.set(file("$buildDir/reports/detekt/archReport.html"))
            }
        }
    }
}
