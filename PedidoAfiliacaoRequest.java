package br.projeto.redemaissocial.dto;

public class PedidoAfiliacaoRequest {
    private String requesterName;
    private String email;

    public String getRequesterName() { return requesterName; }
    public void setRequesterName(String requesterName) { this.requesterName = requesterName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
