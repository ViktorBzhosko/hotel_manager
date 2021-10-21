package by.mycom.ita.controller;

import by.mycom.ita.dto.CommonUserDto;
import by.mycom.ita.model.CommonUser;
import by.mycom.ita.services.ICommonUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @PostMapping("/create/client")
    public CommonUserDto createClient(@RequestBody CommonUserDto commonUserDto) {
        final CommonUser commonUser = objectMapper.convertValue(commonUserDto, CommonUser.class);
        CommonUser userCreated = iServiceCommonUser.createClient(commonUser);
        return objectMapper.convertValue(userCreated, CommonUserDto.class);
    }

    @PostMapping("/create/manager")
    public CommonUserDto createManager(@RequestBody CommonUserDto commonUserDto) {
        final CommonUser commonUser = objectMapper.convertValue(commonUserDto, CommonUser.class);
        CommonUser userCreated = iServiceCommonUser.createManager(commonUser);
        return objectMapper.convertValue(userCreated, CommonUserDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        iServiceCommonUser.deleteById(id);
    }
}
