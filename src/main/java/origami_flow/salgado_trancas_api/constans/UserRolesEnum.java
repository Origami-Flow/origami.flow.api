package origami_flow.salgado_trancas_api.constans;

public enum UserRolesEnum {

    ADMIN("admin"),
    USER("user");

    private String role;

    UserRolesEnum(String role) {
        this.role = role;
    }

    String getRole() {
        return role;
    }
}
