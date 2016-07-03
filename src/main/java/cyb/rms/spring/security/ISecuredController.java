package cyb.rms.spring.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@PreAuthorize("hasRole('ROLE_USER')")
@Component
public interface ISecuredController {

}
