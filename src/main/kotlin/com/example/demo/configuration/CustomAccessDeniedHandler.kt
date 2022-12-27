package com.example.demo.configuration

import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.access.AccessDeniedHandler
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAccessDeniedHandler : AccessDeniedHandler {
    var logger = LoggerFactory.getLogger(CustomAccessDeniedHandler::class.java)

    @Throws(IOException::class, ServletException::class)
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exc: AccessDeniedException) {
        val auth = SecurityContextHolder.getContext().authentication
        if (auth != null) {
            logger.warn("User: " + auth.name
                    + " attempted to access the protected URL: "
                    + request.requestURI)

        } else {
            logger.info("Access denied to " + request.requestURI)
        }
        response.sendRedirect(request.contextPath + "/access-denied")
    }
}
