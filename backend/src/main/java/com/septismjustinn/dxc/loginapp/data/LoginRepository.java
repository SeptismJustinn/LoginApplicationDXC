package com.septismjustinn.dxc.loginapp.data;

import com.septismjustinn.dxc.loginapp.models.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoginRepository extends JpaRepository<Login, UUID> {
}
