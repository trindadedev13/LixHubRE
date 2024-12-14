package dev.trindadedev.lixhub.dialog.accounts;

import android.content.Context;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.credentials.Credential;
import androidx.credentials.CredentialManager;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.GetCustomCredentialOption;
import androidx.credentials.PasswordCredential;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import dev.trindadedev.lixhub.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executors;

public class AccountsSheet extends BottomSheetDialog {

  private Context context;
  private View accountsLayout;
  private AccountsAdapter accountsAdapter;
  private RecyclerView accountsList;

  private CredentialManager credentialManager;
  private GetCustomCredentialOption googleIdOption;

  public AccountsSheet(@NonNull Context context) {
    super(context);
    this.context = context;
    init();
  }

  private void init() {
    initCredentialManager();

    accountsLayout = LayoutInflater.from(context).inflate(R.layout.sheet_accounts, null);
    accountsList = accountsLayout.findViewById(R.id.accounts_list);
    accountsList.setLayoutManager(new LinearLayoutManager(context));

    accountsAdapter = new AccountsAdapter(getDefaultAccounts());
    accountsList.setAdapter(accountsAdapter);

    setContentView(accountsLayout);

    fetchAccounts(
        new CredentialManagerCallback<List<Account>, GetCredentialException>() {
          @Override
          public void onResult(List<Account> result) {
            accountsAdapter.updateAccounts(result);
          }

          @Override
          public void onError(GetCredentialException e) {
            e.printStackTrace();
          }
        });
  }

  private void initCredentialManager() {
    credentialManager = CredentialManager.create(context);

    Bundle requestData = new Bundle();
    requestData.putString("key", "google_id");

    Bundle candidateQueryData = new Bundle();
    candidateQueryData.putString("queryKey", "value");

    googleIdOption =
        new GetCustomCredentialOption(
            requestData, "google_id", candidateQueryData, false, false, new HashSet<>(), 0);
  }

  private void fetchAccounts(
      CredentialManagerCallback<List<Account>, GetCredentialException> callback) {
    GetCredentialRequest request =
        new GetCredentialRequest.Builder().addCredentialOption(googleIdOption).build();

    credentialManager.getCredentialAsync(
        context,
        request,
        new CancellationSignal(),
        Executors.newSingleThreadExecutor(),
        new CredentialManagerCallback<GetCredentialResponse, GetCredentialException>() {
          @Override
          public void onResult(GetCredentialResponse result) {
            Credential credential = result.getCredential();
            List<Account> accounts = new ArrayList<>();

            if (credential instanceof PasswordCredential) {
              PasswordCredential passwordCredential = (PasswordCredential) credential;

              String displayName = passwordCredential.getId();
              String avatarUrl = "https://example.com/avatar.png";

              accounts.add(new Account(displayName, passwordCredential.getId(), avatarUrl));
            }

            callback.onResult(accounts);
          }

          @Override
          public void onError(GetCredentialException e) {
            callback.onError(e);
          }
        });
  }

  public void setAccountListener(AccountListener accountListener) {
    accountsAdapter.setAccountListener(accountListener);
  }

  private List<Account> getDefaultAccounts() {
    List<Account> accounts = new ArrayList<>();
    String validImage = "https://github.com/trindadedev13.png";
    accounts.add(new Account("John Doe", "john.doe@example.com", validImage));
    accounts.add(new Account("Jane Smith", "jane.smith@example.com", validImage));
    accounts.add(new Account("Alice Johnson", "alice.johnson@example.com", validImage));
    accounts.add(new Account("Bob Brown", "bob.brown@example.com", validImage));
    return accounts;
  }
}
