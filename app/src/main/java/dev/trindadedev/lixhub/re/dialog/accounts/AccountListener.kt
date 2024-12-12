package dev.trindadedev.lixhub.re.dialog.accounts;

@FunctionalInterface
interface AccountListener {
  fun onPicked(account: Account, position: Int)
}