package com.io.jst.service.userServices;

import com.io.jst.model.UserGrade;
import com.io.jst.model.dto.UsersDto;
import com.io.jst.model.entity.Users;
import com.io.jst.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void join(UsersDto usersDto){

        ModelMapper modelMapper = new ModelMapper();
        String password = usersDto.getPassword();

        Users users = modelMapper.map(usersDto, Users.class);
        String endPassword = encoder.encode(password);
        users.setPassword(endPassword);
        users.setRole(UserGrade.COMMON.getValue());

        userRepository.save(users);
    }

}
