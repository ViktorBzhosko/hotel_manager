package by.mycom.ita.services;

import by.mycom.ita.dto.CommonUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "hotel-app")
public interface UserFeignClient {

    @PostMapping("/users/create")
    CommonUserDto createUser(CommonUserDto commonUserDto);

}
