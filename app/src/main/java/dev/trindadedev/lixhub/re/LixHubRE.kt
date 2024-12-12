package dev.trindadedev.lixhub.re

import android.app.Application
import com.google.android.material.color.DynamicColors

class LixHubRE: Application() {
  override fun onCreate() {
    DynamicColors.applyToActivitiesIfAvailable(this)
    super.onCreate()
  }
}
