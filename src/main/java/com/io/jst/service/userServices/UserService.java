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

import java.util.HashMap;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void join(UsersDto usersDto) {

        String password = usersDto.getPassword();

        String endPassword = encoder.encode(password);

        usersDto.setAddress(usersDto.getZipcode()+" "+usersDto.getAddressMain()+" "+usersDto.getAddressDetail());
        if(usersDto.getUsername().equals("ADMIN"))
        {
            usersDto.setRole(UserGrade.ADMIN.getValue());
        }else if (usersDto.getUsername().equals("VIP")) {
            usersDto.setRole(UserGrade.VIP.getValue());
        }else {
            usersDto.setRole(UserGrade.COMMON.getValue());
        }

        Users users = Users.builder()
                .role(usersDto.getRole())
                .email(usersDto.getEmail())
                .username(usersDto.getUsername())
                .address(usersDto.getAddress())
                .birthday(usersDto.getBirthday())
                .password(endPassword)
                .phoneNumber(usersDto.getPhoneNumber())
                .build();

        userRepository.save(users);

    }

    @Transactional(readOnly = true)
    public UsersDto findByUserName(String username) {

        Users users = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                });

        ModelMapper modelMapper = new ModelMapper();
        UsersDto usersDto = modelMapper.map(users, UsersDto.class);

        return usersDto;
    }


    @Transactional(readOnly = true)
    public HashMap<String, Boolean> errorCheck(UsersDto usersDto){

        HashMap<String, Boolean>  hashMap = new HashMap<String, Boolean>();
        hashMap.put("username", false);
        hashMap.put("phoneNumber", false);
        hashMap.put("email", false);

        if(userRepository.findByUsername(usersDto.getUsername()).isPresent()){
            hashMap.put("username", true);
        }
        if(userRepository.findByPhoneNumber(usersDto.getPhoneNumber()).isPresent()){
            hashMap.put("phoneNumber", true);
        }
        if(userRepository.findByEmail(usersDto.getEmail())!=null){
            hashMap.put("email", true);
        }
        return hashMap;
    }
}
