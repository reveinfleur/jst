package com.io.jst.service.shopService;

import com.io.jst.model.dto.CSBoardDto;
import com.io.jst.model.dto.ShopDto;
import com.io.jst.model.entity.CustomerBoard;
import com.io.jst.model.entity.Shop;
import com.io.jst.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    @Transactional(readOnly = true)
    public Page<ShopDto> list(Pageable pageable){

        Page<Shop> page = shopRepository.findAll(pageable);


        Page<ShopDto> map = page.map(Shop->new ShopDto(Shop.getId(), Shop.getPrice(), Shop.getProductName(),
                 Shop.getContent(), Shop.getFileName(), Shop.getFilePath(), Shop.getStartDay(), Shop.getEndDay()));

        return map;
    }

    @Transactional
    public void save(ShopDto shopDto, MultipartFile multipartFile) throws IOException {

        String projectPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid+"_"+multipartFile.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        multipartFile.transferTo(saveFile);

        ModelMapper modelMapper = new ModelMapper();

        Shop shop = modelMapper.map(shopDto, Shop.class);

        shop.setFileName(fileName);
        shop.setFilePath("/files/"+fileName);

        System.out.println(shopDto.toString());

        shopRepository.save(shop);

    }

    @Transactional(readOnly = true)
    public ShopDto findDetail(long id){
        ModelMapper modelMapper = new ModelMapper();

        Shop shop = shopRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                }); // 영속화 완료
        ShopDto map =  modelMapper.map(shop, ShopDto.class);
        return map;
    }
}
