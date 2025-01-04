package com.example.qlks_springboot.controller;

import com.example.qlks_springboot.entity.*;
import com.example.qlks_springboot.service.Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class pageController {
    @Autowired
    private Service service;


    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "home";
    }

    @RequestMapping("ql_kh")
    public String ql_khachhang(Model model) {
        List<Customer> khachhang = service.getAllCustomer();
        model.addAttribute("khachhang", khachhang);
        return "ql_khachhang";
    }

    @RequestMapping("ql_p")
    public String ql_phong(Model model) {
        List<Room> rooms = service.getAllByOrderByRoomNumberAsc();
        model.addAttribute("rooms", rooms);
        model.addAttribute("room", new Room());
        return "ql_phong";
    }

    @RequestMapping("ql_dp")
    public String ql_datphong(Model model) {
        List<Booking> bookings = service.getAllBooking();
        model.addAttribute("bookings", bookings);
        return "ql_datphong";
    }

    @RequestMapping("ql_tk")
    public String ql_taikhoan(Model model) {
        List<User> taikhoan = service.getAllUsers();
        model.addAttribute("taikhoan", taikhoan);
        return "ql_taikhoan";
    }

    @RequestMapping("ql_tt")
    public String ql_thanhtoan(Model model) {
        List<Payment> thanhtoan = service.getAllPayment();
        model.addAttribute("thanhtoan", thanhtoan);
        return "ql_thanhtoan";
    }

    @RequestMapping("bctk")
    public String ql_baocaothongke(Model model) {
        model.addAttribute("user", new User());
        return "baoocaothongke";
    }

    @RequestMapping("index")
    public String Main(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @RequestMapping("loginUser")
    public String login_(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("user") User user, Model model, HttpSession session) {
        User loggedInUser = service.loginUser(user.getUsername(), user.getPassword());

        if (loggedInUser != null) {
            session.setAttribute("currentUser", loggedInUser);
            session.setAttribute("currentUserPosition", loggedInUser.getPosition());
            // Check user's position and redirect accordingly
            if (loggedInUser.getPosition().equalsIgnoreCase("admin")) {
                // Admin can access all pages
                return "index";
            } else if (loggedInUser.getPosition().equalsIgnoreCase("Nhân viên")) {

                // Nhân viên cannot access the "Quản lý nhân viên" page
                return "index";
            } else { // Người dùng
                // Redirect Người dùng to the 'userPage'
                return "ql_thanhtoan";
            }
        } else {
            model.addAttribute("errorMessage", "true");
            return "login";
        }
    }


    @RequestMapping(value = "CRUroom", method = RequestMethod.POST)
    public String handleRoomAction(@ModelAttribute("room") Room room, BindingResult bindingResult, Model model, @RequestParam(name = "action", required = false) String action) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Lỗi khi thêm/sửa phòng. Vui lòng kiểm tra lại thông tin.");
            return ql_phong(model);
        }

        if ("add".equals(action)) {
            // Thêm logic kiểm tra các trường
            if (bindingResult.hasErrors()) {
                model.addAttribute("errorMessage", "Lỗi khi thêm phòng. Vui lòng kiểm tra lại thông tin.");
                return ql_phong(model);
            }
            // Thêm logic kiểm tra các trường
            if (room.getRoomNumber() == null || room.getRoomNumber().trim().isEmpty() ||
                    room.getRoomType() == null || room.getRoomType().trim().isEmpty() ||
                    room.getPrice() == null || room.getPrice().trim().isEmpty() ||
                    room.getStatus() == null || room.getStatus().trim().isEmpty()) {
                model.addAttribute("errorMessage", "Vui lòng điền đầy đủ thông tin phòng.");
                return ql_phong(model);
            }
            try {
                service.saveRoom(room);
                model.addAttribute("successMessage", "Thêm mới phòng thành công!");
                return ql_phong(model);

            } catch (RuntimeException e) {
                model.addAttribute("errorMessage", e.getMessage());
                return ql_phong(model);
            }
        } else if ("update".equals(action)) {
            // Thêm logic kiểm tra các trường
            if (bindingResult.hasErrors()) {
                model.addAttribute("errorMessage", "Lỗi khi sửa phòng. Vui lòng kiểm tra lại thông tin.");
                return ql_phong(model);
            }
            // Thêm logic kiểm tra các trường
            if (room.getRoomId()==null) {
                model.addAttribute("errorMessage", "Vui lòng nhập id phòng.");
                return ql_phong(model);
            }
            try {
                service.updateRoom(room);
                model.addAttribute("successMessage", "Cập nhật phòng thành công!");
                return ql_phong(model);
            } catch (RuntimeException e) {
                model.addAttribute("errorMessage", e.getMessage());
                return ql_phong(model);
            }
        } else {
            // Xử lý reset
            return ql_phong(model);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteRoom(@RequestParam("id") Long roomId, Model model) {
        service.deleteRoom(roomId);
        return "redirect:/ql_p"; // trở về trang danh sách phòng
    }

    @RequestMapping(value = "/findRoom", method = RequestMethod.GET)
    public String findUser(@RequestParam("searchTerm") String searchTerm, Model model) {
        List<Room> rooms = service.searchRoom(searchTerm);
        model.addAttribute("rooms", rooms);
        model.addAttribute("room", new Room());
        return "ql_phong";
    }

}
