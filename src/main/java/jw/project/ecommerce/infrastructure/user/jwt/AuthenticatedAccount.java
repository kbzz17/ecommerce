package jw.project.ecommerce.infrastructure.user.jwt;

import java.util.List;

public record AuthenticatedAccount(String email, List<String> role) {
}
