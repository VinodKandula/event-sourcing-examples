package net.chrisrichardson.eventstore.javaexamples.banking.backend.queryside.accounts;

import java.util.List;

public class AccountQueryService {

  private AccountInfoRepository accountInfoRepository;

  public AccountQueryService(AccountInfoRepository accountInfoRepository) {
    this.accountInfoRepository = accountInfoRepository;
  }

  public AccountInfo findByAccountId(String accountId) {
    AccountInfo account = accountInfoRepository.findOne(accountId);
    if (account == null)
      throw new AccountNotFoundException(accountId);
    else
      if(account.getTransferStates()!=null)
        account.getTransactions().stream().forEach(ati -> ati.setStatus(account.getTransferStates().get(ati.getTransactionId())));
      return account;
  }

  public List<AccountInfo> findByCustomerId(String customerId) {
      return accountInfoRepository.findByCustomerId(customerId);
  }
}
