package org.example.BloggingProject.config;


//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig  {
//    @Autowired
//    DataSource dataSource;
//
//    private final UserServiceImpl userService;
//
//    public WebSecurityConfig(UserServiceImpl userService) {
//        this.userService = userService;
//    }

//    @Bean
//    protected PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder(10);
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(encoder());
//    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                    .antMatchers("/", "/api/post/", "/api/post/search/", "/api/init", "api/post/{id}").permitAll()
//                    .anyRequest().authenticated()
//                .and()
//                    .formLogin().defaultSuccessUrl("/", false)
//                .and()
//                    .logout().permitAll();
//
//
//    }

}
