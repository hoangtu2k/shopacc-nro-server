package com.example.shopaccnro.shopaccnroserver.controller;

import com.example.shopaccnro.shopaccnroserver.entity.Category;
import com.example.shopaccnro.shopaccnroserver.entity.Server;
import com.example.shopaccnro.shopaccnroserver.request.AttributeProductRequest;
import com.example.shopaccnro.shopaccnroserver.service.CategoryService;
import com.example.shopaccnro.shopaccnroserver.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/servers")
public class ServerRest {

    @Autowired
    private ServerService serverService;

    @GetMapping()
    public ResponseEntity<List<AttributeProductRequest>> getAllServers() {
        List<Server> servers = serverService.getAllServer();

        // Chuyển đổi danh sách User sang danh sách UserReq trực tiếp
        List<AttributeProductRequest> attributeProductReqs = servers.stream()
                .map(server  -> {
                    AttributeProductRequest req = new AttributeProductRequest();
                    req.setId(server.getId());
                    req.setName(server.getName());
                    return req; // Trả về đối tượng UserReq đã tạo
                })
                .collect(Collectors.toList()); // Thu thập kết quả vào danh sách

        if (attributeProductReqs.isEmpty()) {
            return ResponseEntity.noContent().build(); // Trả về 204 nếu không có người dùng
        }
        return ResponseEntity.ok(attributeProductReqs); // Trả về 200 và danh sách người dùng
    }

}
