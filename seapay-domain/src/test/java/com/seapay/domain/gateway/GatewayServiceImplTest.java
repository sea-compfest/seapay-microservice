package com.seapay.domain.gateway;

import com.seapay.api.gateway.GatewayService;
import com.seapay.api.gateway.request.PayRequest;
import com.seapay.api.gateway.request.RegisterCustomerRequest;
import com.seapay.api.gateway.request.RegisterMerchantRequest;
import com.seapay.api.gateway.request.TopupRequest;
import com.seapay.api.gateway.response.PayResponse;
import com.seapay.api.gateway.response.TopupResponse;
import com.seapay.api.transaction.TransactionService;
import com.seapay.api.useraccount.UserAccountService;
import com.seapay.api.wallet.WalletService;
import com.seapay.domain.transaction.TransactionRepository;
import com.seapay.domain.transaction.TransactionServiceImpl;
import com.seapay.domain.transaction.entity.Transaction;
import com.seapay.domain.transaction.repository.TransactionRepositoryImpl;
import com.seapay.domain.useraccount.UserAccountRepository;
import com.seapay.domain.useraccount.UserAccountServiceImpl;
import com.seapay.domain.useraccount.entity.UserAccount;
import com.seapay.domain.useraccount.repository.UserAccountRepositoryImpl;
import com.seapay.domain.utils.DatabaseUtils;
import com.seapay.domain.wallet.WalletRepository;
import com.seapay.domain.wallet.WalletServiceImpl;
import com.seapay.domain.wallet.entity.Wallet;
import com.seapay.domain.wallet.repository.WalletRepositoryImpl;
import org.junit.jupiter.api.*;

import static com.seapay.domain.constant.SeaPayConstant.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GatewayServiceImplTest {

    private UserAccountRepository userAccountRepository = new UserAccountRepositoryImpl();
    private WalletRepository walletRepository = new WalletRepositoryImpl();
    private TransactionRepository transactionRepository = new TransactionRepositoryImpl();

    private UserAccountService userAccountService = new UserAccountServiceImpl(userAccountRepository);
    private WalletService walletService = new WalletServiceImpl(walletRepository);
    private TransactionService transactionService = new TransactionServiceImpl(transactionRepository);

    private GatewayService gatewayService = new GatewayServiceImpl(userAccountService, walletService, transactionService);

    static UserAccount customerAccount;
    static UserAccount merchantAccount;

    @BeforeAll
    static void setUp() {
        DatabaseUtils.truncate("user_accounts");
        DatabaseUtils.truncate("wallets");
        DatabaseUtils.truncate("transactions");
    }

    @Test
    @Order(1)
    void registerCustomerTest() {
        RegisterCustomerRequest registerCustomerRequest = new RegisterCustomerRequest("james bond","jamesbond@seapay.co.id", "081313131313");

        gatewayService.registerCustomer(registerCustomerRequest);

        customerAccount = userAccountRepository.getByEmail(registerCustomerRequest.getEmail());

        assertEquals(registerCustomerRequest.getName(), customerAccount.getName());
        assertEquals(registerCustomerRequest.getEmail(), customerAccount.getEmail());
        assertEquals(registerCustomerRequest.getPhonenumber(), customerAccount.getPhonenumber());
        assertEquals(CUSTOMER_TYPE, customerAccount.getUserType());
    }

    @Test
    @Order(2)
    void registerMerchantTest() {
        RegisterMerchantRequest registerMerchantRequest = new RegisterMerchantRequest("warung kopi","warungkopi@seapay.co.id");

        gatewayService.registerMerchant(registerMerchantRequest);

        merchantAccount = userAccountRepository.getByEmail(registerMerchantRequest.getEmail());

        assertEquals(registerMerchantRequest.getName(), merchantAccount.getName());
        assertEquals(registerMerchantRequest.getEmail(), merchantAccount.getEmail());
        assertEquals(MERCHANT_TYPE, merchantAccount.getUserType());
    }

    @Test
    @Order(3)
    void topupTest() {
        Long topUpAmount = 1000L;
        TopupRequest topupRequest = new TopupRequest(customerAccount.getUserID(), topUpAmount, "ABC123", "Pembayaran Top Up");
        Long previousBalance = walletRepository.getByUserID(customerAccount.getUserID()).getBalance();

        TopupResponse response = gatewayService.topup(topupRequest);

        Transaction actualTransaction = transactionRepository.getByTransactionID(response.getTransactionID());
        Wallet actualWallet = walletRepository.getByUserID(customerAccount.getUserID());

        assertEquals(topupRequest.getAmount(), actualTransaction.getAmount());
        assertEquals(topupRequest.getReferenceID(), actualTransaction.getReferenceID());
        assertEquals(topupRequest.getDescription(), actualTransaction.getDescription());

        assertEquals(customerAccount.getUserID(), actualWallet.getUserID());
        assertEquals(previousBalance + topUpAmount, actualWallet.getBalance());
    }

    @Test
    @Order(4)
    void payTest() {
        Long amountToPay = 500L;
        PayRequest payRequest = new PayRequest(customerAccount.getUserID(), merchantAccount.getUserID(), amountToPay, "DEF456", "Pembelian Kopi");
//        Long customerPreviousBalance = walletRepository.getByUserID(customerAccount.getUserID()).getBalance();
//        Long merchantPreviousBalance = walletRepository.getByUserID(merchantAccount.getUserID()).getBalance();

        PayResponse response = gatewayService.pay(payRequest);

        Transaction actualTransaction = transactionRepository.getByTransactionID(response.getTransactionID());

        assertEquals(payRequest.getAmount(), actualTransaction.getAmount());
        assertEquals(payRequest.getReferenceID(), actualTransaction.getReferenceID());
        assertEquals(payRequest.getDescription(), actualTransaction.getDescription());

//        Wallet customerActualWallet = walletRepository.getByUserID(customerAccount.getUserID());
//
//        assertEquals(customerAccount.getUserID(), customerActualWallet.getUserID());
//        assertEquals(customerPreviousBalance - amountToPay, customerActualWallet.getBalance());
//
//        Wallet merchantActualWallet = walletRepository.getByUserID(merchantAccount.getUserID());
//
//        assertEquals(merchantAccount.getUserID(), merchantActualWallet.getUserID());
//        assertEquals(merchantPreviousBalance + amountToPay, merchantActualWallet.getBalance());
    }

}