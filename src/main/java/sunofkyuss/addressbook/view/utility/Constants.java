package sunofkyuss.addressbook.view.utility;

public final class Constants {

	private Constants() {
	}

	public static final String InputMaskBootify = "form-control bf-no-message has-success form-group";
	public static final String PhoneNumberMask = "(99) 999-999-9999";
	public static final String AlphaKeyFilter = "/[a-z_ığüşöçİ ]/i";
	public static final String SpacedAlphaRegexPattern = "^[a-zA-Z_ığüşöçİĞÜŞÖÇ]+( [a-zA-Z_ığüşöçİĞÜŞÖÇ]+)*$";
	public static final String NullableSpacedAlphaNumericPuncRegexPattern="(^[a-zA-Z0-9_ığüşöçİĞÜŞÖÇ.,:;/]+( [a-zA-Z0-9_ığüşöçİĞÜŞÖÇ.,:;/]+)*$)?";
	public static final String SpacedAlphaNumericPuncRegexPattern="^[a-zA-Z0-9_ığüşöçİĞÜŞÖÇ.,:;/]+( [a-zA-Z0-9_ığüşöçİĞÜŞÖÇ.,:;/]+)*$";
	public static final String AlphaNumericPuncKeyFilter="/[a-z0-9_ığüşöçİ.,:;/ ]/i";
	public static final String NullableEMailRegexPattern = "(([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+))?";
	public static final String EMailRegexPattern = "(([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+))";
}
