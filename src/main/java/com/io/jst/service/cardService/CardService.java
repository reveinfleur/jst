package com.io.jst.service.cardService;

import com.io.jst.model.dto.UserCreditCardDto;
import com.io.jst.model.dto.PayPrice;
import com.io.jst.model.entity.Shop;
import com.io.jst.model.entity.UserCreditCard;
import com.io.jst.model.entity.Users;
import com.io.jst.repository.CreditCardRepository;
import com.io.jst.repository.ShopRepository;
import com.io.jst.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CreditCardRepository creditCardRepository;
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    public UserCreditCardDto userCreditCardDto;


    @Transactional
    public void addCard(UserCreditCardDto userCreditCardDto){
        userCreditCardDto.setPrice(100000);
        ModelMapper modelMapper = new ModelMapper();
        UserCreditCard userCreditCard = modelMapper.map(userCreditCardDto, UserCreditCard.class);
        creditCardRepository.save(userCreditCard);
    }

    @Transactional(readOnly = true)
    public UserCreditCardDto findById(Long id){
        UserCreditCard userCreditCard = creditCardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                }); // 영속화 완료
        ModelMapper modelMapper = new ModelMapper();

        UserCreditCardDto userCreditCardDto = modelMapper.map(userCreditCard, UserCreditCardDto.class);

        return userCreditCardDto;
    }

    @Transactional
    public void goPayment(String CardName, int price, String username, Long id){
        Users users = userRepository.findByUsername(username).orElseThrow(()->{
            return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
        });

        Shop shop = shopRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
        });

        UserCreditCard userCreditCard = creditCardRepository.findByCardName(CardName).orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 해당카드를 찾을 수 없습니다.");
        }); // 영속화 완료


        shop.setUsers(users);
        userCreditCard.setPrice(userCreditCard.getPrice()-price);

    }

    @Transactional(readOnly = true)
    public Boolean errorCheck(PayPrice payPrice){

        UserCreditCard userCreditCard = creditCardRepository.findByCardName(payPrice.getCardName())
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                }); // 영속화 완료
       if(userCreditCard.getPrice()< payPrice.getPrice()){
           return false;
       }

        return true;
    }

    public void setCardSelect(UserCreditCardDto userCreditCardDto){
        this.userCreditCardDto = userCreditCardDto;
    }
    public UserCreditCardDto getCardSelect(){
        return userCreditCardDto;
    }


}
