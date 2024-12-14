package dev.trindadedev.lixhub.dialog.accounts

@FunctionalInterface
interface AccountListener {
  fun onPicked(account: Account, position: Int)
}
