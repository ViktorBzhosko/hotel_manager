package by.mycom.ita.controller;

import by.mycom.ita.dto.CommonUserDto;
import by.mycom.ita.model.CommonUser;
import by.mycom.ita.services.ICommonUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class CommonUserController {

    private final ObjectMapper objectMapper;
    private final ICommonUserService iServiceCommonUser;

    public CommonUserController(ObjectMapper objectMapper, ICommonUserService iServiceCommonUser) {
        this.objectMapper = objectMapper;
        this.iServiceCommonUser = iServiceCommonUser;
    }

    @ApiOperation(value = "Create user")
    @PostMapping("/create")
    public CommonUserDto createUser(@RequestBody CommonUserDto commonUserDto) {
        final CommonUser commonUser = objectMapper.convertValue(commonUserDto, CommonUser.class);
        CommonUser userCreated = iServiceCommonUser.create(commonUser);
        return objectMapper.convertValue(userCreated, CommonUserDto.class);
    }
}
