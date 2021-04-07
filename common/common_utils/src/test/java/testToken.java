import com.mzxssg.yygh.common.helper.JwtHelper;

/**
 * @Author: Zexin Ma
 * @Create: 2021-04-07-15:07
 * @Description:
 */
public class testToken {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSiox099ANDXYNUtJRSq0oULIyNDM0tzAzMDQ11VEqLU4t8kxRsrKEMP0Sc1OBWp5u6H82o0-pFgD4z8GqQwAAAA.XoojLrof_nbmGa3DwpAFvj8ciGqulDWhbQJABjT0gdGTEtOdCAghfmruy5xpGRrlZVagR2T88cSjAS81j5xnmg";
        String userName = JwtHelper.getUserName(token);
        Long userId = JwtHelper.getUserId(token);
        System.out.println(userName);
        System.out.println(userId);
    }
}
