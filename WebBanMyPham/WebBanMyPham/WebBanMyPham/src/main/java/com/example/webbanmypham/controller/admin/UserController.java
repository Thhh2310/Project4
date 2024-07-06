package com.example.webbanmypham.controller.admin;

import com.example.webbanmypham.FunctionalAccessory.RandomID;
import com.example.webbanmypham.dto.UserDTO;
import com.example.webbanmypham.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import output.UserOutput;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/userList")
    public UserOutput getAll(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        UserOutput result = new UserOutput();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(userService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (userService.totalItem()) / limit));
        model.addAttribute("userOutput", result);
        return result;
    }

    @GetMapping("/userByUserName/list/{userName}")
    public UserOutput getByUsername(@PathVariable String userName, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        UserOutput result = new UserOutput();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(userService.getByUserName(userName,pageable));
        result.setTotalPage((int) Math.ceil((double) (userService.totalItem()) / limit));
        model.addAttribute("userOutput", result);
        return result;
    }
    @GetMapping("/userByIsAdmin/list/{isAdmin}")
    public UserOutput getByIsAdmin(@PathVariable int userID, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        UserOutput result = new UserOutput();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(userService.getByIsAdmin(userID,pageable));
        result.setTotalPage((int) Math.ceil((double) (userService.totalItem()) / limit));
        model.addAttribute("userOutput", result);
        return result;
    }
    @GetMapping("/userByID/{userID}")
    public ResponseEntity<?> getByUserID(@PathVariable int userID) {
        try {
            UserDTO userDTO = userService.getByUserID(userID);

            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((List<UserDTO>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        try {
            String randomID = RandomID.generateRandomID(6);
            userDTO.setUserID(Integer.parseInt(randomID));
            userService.createUser(userDTO);
            return new ResponseEntity<>("more success " , HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateUser/{userID}")
    public ResponseEntity<String> updateUser(@PathVariable int userID, @RequestBody UserDTO userDTO) {
        try {
            userDTO.setUserID(userID);
            userService.updateUser(userDTO);
            return new ResponseEntity<>(userDTO+"Cập nhật quyền người dùng thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // xóa dữ liệu
    @Transactional
    @DeleteMapping("/deleteUser/{userID}")
    public ResponseEntity<String> deleteUser(@PathVariable int userID) {
        try {
            userService.deleteUser(userID);
            return new ResponseEntity<>("Xóa nguời dùng thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


//@RestController
//@RequestMapping("/admin/user")
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/getAll")
//    public List<UserDTO> getAll(){
//        try {
//            List<UserDTO> userDTO = userService.getAll();
//            return userDTO;
//        }catch (Exception e){
//          return null;
//        }
//    }
//    @GetMapping("/getByUserName/{userName}")
//    public List<UserDTO> getByUserName(@PathVariable String userName){
//        try {
//            List<UserDTO> dto = userService.getUserByUserName(userName);
//            return dto;
//        }catch (Exception e){
//            return null;
//        }
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
//        try {
//            userService.createUser(userDTO);
//            return new ResponseEntity<>("more success ", HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<String> updateUser(@PathVariable Integer userID, @RequestBody UserDTO userDTO) {
//        try {
//            userDTO.setUserID(userID);
//            userService.updateUser(userDTO);
//            return new ResponseEntity<>("Cập nhật người dùng thành công", HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @Transactional
//    @DeleteMapping("/delete/{userID}")
//    public ResponseEntity<String> deleteUser(@PathVariable Integer userID) {
//        try {
//            userService.deleteUser(userID);
//            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
