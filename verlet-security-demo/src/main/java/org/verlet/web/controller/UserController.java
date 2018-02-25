package org.verlet.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.verlet.dto.UserDTO;
import org.verlet.exception.UserException;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author verlet
 * @date 2017/12/31
 */
@RestController
public class UserController {

    @GetMapping("/user")
    @JsonView(UserDTO.UserDetailView.class)
    public List<UserDTO> query(
            //使用org.hibernate.validator验证需要加上 @Valid
            @Valid UserDTO userDTO,
            @PageableDefault(page = 1, size = 10, sort = "username,asc") Pageable pageable) {
        System.out.println(userDTO);
        System.out.println(ReflectionToStringBuilder.toString(pageable, ToStringStyle.MULTI_LINE_STYLE));
        List<UserDTO> list = new LinkedList<>();
        list.add(new UserDTO());
        list.add(new UserDTO());
        list.add(new UserDTO());
        return list;
    }

    /**
     * :\\d+  使用正则表达式 表示只能接受数字
     *
     * @return
     * @parSample str
     */
    @GetMapping("/name/{str:\\d+}")
    public UserDTO aueryByName(@PathVariable("str") String str) {
        System.out.println("name=" + str);
        UserDTO userDTO = new UserDTO();
        userDTO.setName(str);
        return userDTO;
    }

    /**
     * Filter->Interceptor->ControllerAdvice->Aspect->Controller
     */
    @GetMapping("/testException")
    public String testException() {
        System.out.println("===============");
//        throw new UserException(1);
        return "";
    }

    @PostMapping("/file")
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        String folder = "";
        File localFile = new File(folder,System.currentTimeMillis()+".text");

        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }
}

@Data
class FileInfo {
    String path;

    public FileInfo(String path) {
        this.path = path;
    }
}