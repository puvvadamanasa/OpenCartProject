package opencart.project.constants;


import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	public static final int DEFAULT_MEDIUM_TIMEOUT=10;
	public static final int DEFAULT_SHORT_TIMEOUT=5;
	public static final int DEFAULT_LONG_TIMEOUT=20;
	
	public static final String LOGIN_PAGE_TITLE_VALUE ="Account Login";
	public static final String LOGIN_PAGE_URL_VALUE ="index.php?route=account/login";
	public static final String ACCOUNTS_PAGE_TITLE_VALUE ="My Account";
	public static final String ACCOUNTS_PAGE_URL_VALUE ="index.php?route=account/account";
	public static final int ACCOUNTS_PAGE_HEADERS_COUNT=4;
	public static final List<String> ACCOUNTS_PAGE_EXPE_HEADERS_LIST = Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	public static final CharSequence USER_REG_SUCCESS_MSG = "Your Account Has Been Created";
	public static final String REGISTER_SHEET_NAME = "register";

}
