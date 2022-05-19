package waa.propertymanagementbackend.service.impl;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import waa.propertymanagementbackend.service.UserService;

@Service
@RequiredArgsConstructor
@Slf4j
public class UaaServiceImpl {
/*
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtHelper jwtHelper;
    @Autowired
    private UserRepository userRepo;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        var loginResponse = new LoginResponse("", "");
        try {
            var result = authenticationManager.authenticate(

                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword())
            );
            final String accessToken = jwtHelper.generateToken(loginRequest.getEmail());
            final String refreshToken = jwtHelper.generateRefreshToken(loginRequest.getEmail());
            loginResponse = new LoginResponse(accessToken, refreshToken);
        } catch (BadCredentialsException e) {
            log.info("Bad Credentials");
            loginResponse = new LoginResponse("Wrong Credentials", "");
        }


        return loginResponse;
    }



    private ModelMapper modelMapper=new ModelMapper();
    @Autowired
    UserRepository userRepository;

    @Override
    public void save(UsersDto user) {
        User u = new User();
        modelMapper.map(user, u);
        userRepository.save(u);

    }

    @Override
    public List<UsersDto> getAll() {
        List<UsersDto> dtos = new ArrayList<>();

        List<User> p = (List<User>) userRepository.findAll();
        UsersDto dto = new UsersDto();
        for (int i = 0; i < p.size(); i++) {
            modelMapper.map(p.get(i), dto);
            dtos.add(dto);
        }
        return dtos;


    }

    @Override
    public UsersDto getById(int id) {
        UsersDto dto = new UsersDto();
        modelMapper.map(userRepository.findById(id).get(), dto);
        return dto;

    }

    @Override
    public void delete(int id, boolean value) {

        User u = new User();
        u = userRepository.findById(id).get();

        u.setDeleted(value);
        userRepository.save(u);


    }


    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtHelper.validateToken(refreshTokenRequest.getRefreshToken());
        if (isRefreshTokenValid) {
            final String accessToken = jwtHelper.generateToken(jwtHelper.getSubject(refreshTokenRequest.getRefreshToken()));
            var loginResponse = new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken());
            return loginResponse;
        }
        return new LoginResponse();
    }

 */
}
