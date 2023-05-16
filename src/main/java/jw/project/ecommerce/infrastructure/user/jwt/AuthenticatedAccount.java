package jw.project.ecommerce.infrastructure.user.jwt;

import java.util.List;

public record AuthenticatedAccount(Long id, List<String> role) {
}
