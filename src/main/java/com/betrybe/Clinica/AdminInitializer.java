package com.betrybe.Clinica;

import com.betrybe.Clinica.service.AdminService;
import jakarta.annotation.PostConstruct;

public class AdminInitializer {

  private final AdminService adminService;

  public AdminInitializer(AdminService adminService) {
    this.adminService = adminService;
  }

  @PostConstruct
  public void init() {
    adminService.createAdmin();
  }
}
