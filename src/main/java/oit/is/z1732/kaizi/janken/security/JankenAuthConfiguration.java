package oit.is.z1732.kaizi.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class JankenAuthConfiguration {

  /**
   * 認可処理に関する設定（認証されたユーザがどこにアクセスできるか）
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login
        .permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")) // ログアウト後に / にリダイレクト
        .authorizeHttpRequests(authz -> authz
            .requestMatchers(AntPathRequestMatcher.antMatcher("/janken/**")).authenticated() // /janken/以下は認証済みであること
            .requestMatchers(AntPathRequestMatcher.antMatcher("/**")).permitAll()) // それ以外は全員アクセス可能
        .csrf(csrf -> csrf
            .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/*")))
        .headers(headers -> headers
            .frameOptions(frameOptions -> frameOptions
                .sameOrigin()));
    return http.build();
  }

  /**
   * 認証処理に関する設定（誰がどのようなロールでログインできるか）
   *
   * @return
   */
  @Bean
  public InMemoryUserDetailsManager userDetailsService() {

    // ユーザ名，パスワード，ロールを指定してbuildする
    // このときパスワードはBCryptでハッシュ化されているため，{bcrypt}とつける
    // ハッシュ化せずに平文でパスワードを指定する場合は{noop}をつける
    // ハッシュ化されたパスワードを得るには，この授業のbashターミナルで下記のように末尾にユーザ名とパスワードを指定すると良い(要VPN)
    // $ sshrun htpasswd -nbBC 10 user1 p@ss

    // pass : isdev
    UserDetails user1 = User.withUsername("user1")
        .password("{bcrypt}$2y$10$IHshgXSVsJ6VrDYfOFR8Defjt0lF37aU6z59Yr9.YcajG.ysR0D7O").roles("USER").build();
    // pass : isdev
    UserDetails user2 = User.withUsername("user2")
        .password("{bcrypt}$2y$10$eHlpOh6bgteVaBRGwASpoetAzkRY/hiX2s9GP9yyW2zZJ.gbam6CG").roles("USER").build();
    // pass : isdev
    UserDetails user3 = User.withUsername("ほんだ")
        .password("{bcrypt}$2y$10$Lrl5hT.xaQvx2K9aBWSUHuFqQZfCtA/idLkP0ZHY4k6NeLfTg3x66").roles("USER").build();
    // pass : isdev
    UserDetails user4 = User.withUsername("いがき")
        .password("{bcrypt}$2y$10$498jOoN/kcipZ6al.ILTIur.z98OaWNtRs3ii/kSEDtzZHga0qMsO").roles("USER").build();
    /*
     * UserDetails admin = User.withUsername("admin")
     * .password(
     * "{bcrypt}$2y$10$ngxCDmuVK1TaGchiYQfJ1OAKkd64IH6skGsNw1sLabrTICOHPxC0e").roles
     * ("ADMIN").build();
     */

    // 生成したユーザをImMemoryUserDetailsManagerに渡す（いくつでも良い）
    return new InMemoryUserDetailsManager(user1, user2, user3,user4);
  }
}
