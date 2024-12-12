package dev.trindadedev.lixhub.re.dialog.accounts;

@FunctionalInterface
public interface AccountListener {
  void onPicked(Account account, int position);
}