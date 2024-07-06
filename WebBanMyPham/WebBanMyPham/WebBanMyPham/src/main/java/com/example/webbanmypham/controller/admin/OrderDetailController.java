package com.example.webbanmypham.controller.admin;

import com.example.webbanmypham.dto.OrderDTO;
import com.example.webbanmypham.dto.OrderDetailDTO;
import com.example.webbanmypham.dto.OrderRequestDTO;
import com.example.webbanmypham.dto.UserDTO;
import com.example.webbanmypham.service.OrderDetailService;
import com.example.webbanmypham.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import output.OrderDetailOutput;
import output.OrderRequestOutPut;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin/orderdetail")
public class OrderDetailController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/orderID/list/{orderID}")
    public OrderDetailOutput getByOrderID(@PathVariable int orderID, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        OrderDetailOutput result = new OrderDetailOutput();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(orderDetailService.getByOrderID(orderID,pageable));
        result.setTotalPage((int) Math.ceil((double) (orderDetailService.totalItem()) / limit));
        model.addAttribute("OrderDetailOutput", result);
        return result;
    }

    @GetMapping("/orderCancel/list/{orderCancel}")
    public OrderRequestOutPut getByOrderCancel(@PathVariable String orderCancel, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        OrderRequestOutPut result = new OrderRequestOutPut();
        List<OrderRequestDTO> orderRequestDTO = new ArrayList<>();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        List<OrderDTO> orderDTO = orderService.getByOrderCancelUser(orderCancel,pageable);
        for (OrderDTO item: orderDTO
        ) {
            OrderRequestDTO requestDTO = new OrderRequestDTO();
            List<OrderDetailDTO> orderDetailDTO = orderDetailService.getByOrderID(item.getOrderID(),pageable);
            requestDTO.setOrder(item);
            requestDTO.setOrderDetailList(orderDetailDTO);
            orderRequestDTO.add(requestDTO);
        }

        result.setListResult(orderRequestDTO);
        result.setTotalPage((int) Math.ceil((double) (orderService.totalItem()) / limit));
        model.addAttribute("Orderdetailsoutput", result);
        return result;
    }
    @GetMapping("/order/list")
    public OrderRequestOutPut getAll( @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        OrderRequestOutPut result = new OrderRequestOutPut();
        List<OrderRequestDTO> orderRequestDTO = new ArrayList<>();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        List<OrderDTO> orderDTO = orderService.getAll(pageable);
        for (OrderDTO item: orderDTO
        ) {
            OrderRequestDTO requestDTO = new OrderRequestDTO();
            List<OrderDetailDTO> orderDetailDTO = orderDetailService.getByOrderID(item.getOrderID(),pageable);
            requestDTO.setOrder(item);
            requestDTO.setOrderDetailList(orderDetailDTO);
            orderRequestDTO.add(requestDTO);
        }
        result.setListResult(orderRequestDTO);
        result.setTotalPage((int) Math.ceil((double) (orderService.totalItem()) / limit));
        model.addAttribute("OrderDetailOutPut", result);
        return result;
    }
    @GetMapping("/orderByID/{orderID}")
    public ResponseEntity<?> getByUserID(@PathVariable int orderID) {
        try {
            OrderRequestDTO orderRequestDTO = orderService.getByOrderID(orderID);

            return new ResponseEntity<>(orderRequestDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((List<UserDTO>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/orderStatus/list/{orderStatus}")
    public OrderRequestOutPut getByOrderStatus(@PathVariable String orderStatus, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        OrderRequestOutPut result = new OrderRequestOutPut();
        List<OrderRequestDTO> orderRequestDTO = new ArrayList<>();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        List<OrderDTO> orderDTO = orderService.getByOrderStatus(orderStatus,pageable);
        for (OrderDTO item: orderDTO
        ) {
            OrderRequestDTO requestDTO = new OrderRequestDTO();
            List<OrderDetailDTO> orderDetailDTO = orderDetailService.getByOrderID(item.getOrderID(),pageable);
            requestDTO.setOrder(item);
            requestDTO.setOrderDetailList(orderDetailDTO);
            orderRequestDTO.add(requestDTO);
        }

        result.setListResult(orderRequestDTO);
        result.setTotalPage((int) Math.ceil((double) (orderService.totalItem()) / limit));
        model.addAttribute("OrderDetailOutPut", result);
        return result;
    }
    @GetMapping("/orderPay/list/{orderPay}")
    public OrderRequestOutPut getByOrderPay(@PathVariable String orderPay, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        OrderRequestOutPut result = new OrderRequestOutPut();
        List<OrderRequestDTO> orderRequestDTO = new ArrayList<>();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        List<OrderDTO> orderDTO = orderService.getByOrderPay(orderPay,pageable);
        for (OrderDTO item: orderDTO
        ) {
            OrderRequestDTO requestDTO = new OrderRequestDTO();
            List<OrderDetailDTO> orderDetailDTO = orderDetailService.getByOrderID(item.getOrderID(),pageable);
            requestDTO.setOrder(item);
            requestDTO.setOrderDetailList(orderDetailDTO);
            orderRequestDTO.add(requestDTO);
        }

        result.setListResult(orderRequestDTO);
        result.setTotalPage((int) Math.ceil((double) (orderService.totalItem()) / limit));
        model.addAttribute("OrderDetailOutPut", result);
        return result;
    }
    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestBody OrderDetailDTO orderDetailDTO) {
        try {
            orderDetailService.createOrderDetail(orderDetailDTO);
            return new ResponseEntity<>("more success ", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        try {

            orderService.placeOrder(orderRequestDTO.getOrder(), orderRequestDTO.getOrderDetailList());
            return ResponseEntity.ok("Order placed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PutMapping("/updateOrderByID/{orderID}")
    public ResponseEntity<String> updateOrder(@PathVariable int orderID,@RequestBody OrderDTO orderDTO) {
        try {
            orderDTO.setOrderID(orderID);
            orderService.updateOrder(orderDTO);
            return new ResponseEntity<>("more success ", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateOrder/{orderID}")
    public ResponseEntity<String> updateOrder(@PathVariable int orderID,@RequestBody OrderRequestDTO orderDetailDTO) {
        try {
            OrderDTO orderDTO  = orderDetailDTO.getOrder();
            orderDTO.setOrderID(orderID);
            List<OrderDetailDTO> orderDetailList = orderDetailDTO.getOrderDetailList();
            orderService.approveOrder(orderDTO,orderDetailList);
            return new ResponseEntity<>("more success ", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/userID/list/{userID}")
    public OrderRequestOutPut getByUserID(@PathVariable int userID, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        OrderRequestOutPut result = new OrderRequestOutPut();
        List<OrderRequestDTO> orderRequestDTO = new ArrayList<>();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        List<OrderDTO> orderDTO = orderService.getByUserID(userID,pageable);
        for (OrderDTO item: orderDTO
        ) {
            OrderRequestDTO requestDTO = new OrderRequestDTO();
            List<OrderDetailDTO> orderDetailDTO = orderDetailService.getByOrderID(item.getOrderID(),pageable);
            requestDTO.setOrder(item);
            requestDTO.setOrderDetailList(orderDetailDTO);
            orderRequestDTO.add(requestDTO);
        }

        result.setListResult(orderRequestDTO);
        result.setTotalPage((int) Math.ceil((double) (orderService.totalItem()) / limit));
        model.addAttribute("OrderDetailOutPut", result);
        return result;
    }
    @GetMapping("/userID/list/-status1/{userID}")
    public OrderRequestOutPut getByUserIDStatus1(@PathVariable int userID, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        OrderRequestOutPut result = new OrderRequestOutPut();
        List<OrderRequestDTO> orderRequestDTO = new ArrayList<>();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        List<OrderDTO> orderDTO = orderService.getByStatusUser(userID,pageable);
        for (OrderDTO item: orderDTO
        ) {
            OrderRequestDTO requestDTO = new OrderRequestDTO();
            List<OrderDetailDTO> orderDetailDTO = orderDetailService.getByOrderID(item.getOrderID(),pageable);
            requestDTO.setOrder(item);
            requestDTO.setOrderDetailList(orderDetailDTO);
            orderRequestDTO.add(requestDTO);
        }

        result.setListResult(orderRequestDTO);
        result.setTotalPage((int) Math.ceil((double) (orderService.totalItem()) / limit));
        model.addAttribute("OrderDetailOutPut", result);
        return result;
    }
    @GetMapping("/userID/list/-status2/{userID}")
    public OrderRequestOutPut getByUserIDStatus2(@PathVariable int userID, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        OrderRequestOutPut result = new OrderRequestOutPut();
        List<OrderRequestDTO> orderRequestDTO = new ArrayList<>();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        List<OrderDTO> OrderDTO = orderService.getByStatusUser2(userID,pageable);
        for (OrderDTO item: OrderDTO
        ) {
            OrderRequestDTO requestDTO = new OrderRequestDTO();
            List<OrderDetailDTO> orderDetailDTO = orderDetailService.getByOrderID(item.getOrderID(),pageable);
            requestDTO.setOrder(item);
            requestDTO.setOrderDetailList(orderDetailDTO);
            orderRequestDTO.add(requestDTO);
        }

        result.setListResult(orderRequestDTO);
        result.setTotalPage((int) Math.ceil((double) (orderService.totalItem()) / limit));
        model.addAttribute("OrderDetailOutPut", result);
        return result;
    }
    @GetMapping("/userID/list/-status3/{userID}")
    public OrderRequestOutPut getByUserIDStatus3(@PathVariable int userID, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        OrderRequestOutPut result = new OrderRequestOutPut();
        List<OrderRequestDTO> orderRequestDTO = new ArrayList<>();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        List<OrderDTO> OrderDTO = orderService.getByStatusUser3(userID,pageable);
        for (OrderDTO item: OrderDTO
        ) {
            OrderRequestDTO requestDTO = new OrderRequestDTO();
            List<OrderDetailDTO> orderDetailDTO = orderDetailService.getByOrderID(item.getOrderID(),pageable);
            requestDTO.setOrder(item);
            requestDTO.setOrderDetailList(orderDetailDTO);
            orderRequestDTO.add(requestDTO);
        }

        result.setListResult(orderRequestDTO);
        result.setTotalPage((int) Math.ceil((double) (orderService.totalItem()) / limit));
        model.addAttribute("Orderdetailsoutput", result);
        return result;
    }
    @GetMapping("/userID/list/cancel/{userID}")
    public OrderRequestOutPut getByUserIDCancel(@PathVariable int userID, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        OrderRequestOutPut result = new OrderRequestOutPut();
        List<OrderRequestDTO> orderRequestDTO = new ArrayList<>();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        List<OrderDTO> OrderDTO = orderService.getByOrderCancelUser2(userID,pageable);
        for (OrderDTO item: OrderDTO
        ) {
            OrderRequestDTO requestDTO = new OrderRequestDTO();
            List<OrderDetailDTO> orderDetailDTO = orderDetailService.getByOrderID(item.getOrderID(),pageable);
            requestDTO.setOrder(item);
            requestDTO.setOrderDetailList(orderDetailDTO);
            orderRequestDTO.add(requestDTO);
        }

        result.setListResult(orderRequestDTO);
        result.setTotalPage((int) Math.ceil((double) (orderService.totalItem()) / limit));
        model.addAttribute("OrderDetailOutPut", result);
        return result;
    }
}
