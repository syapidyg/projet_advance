package com.advance.pharmacie.model;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "APP_CLIENT")
public class Client extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Long number;

    @Email
    private String email;

    @OneToMany(mappedBy = "client")
    private List<Commande> commandes = new ArrayList<>();


    public static final class ClientBuilder {
        private Long id;
        private String name;
        private Long number;
        private @Email String email;

        private ClientBuilder() {
        }

        public static ClientBuilder aClient() {
            return new ClientBuilder();
        }

        public ClientBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ClientBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ClientBuilder number(Long number) {
            this.number = number;
            return this;
        }

        public ClientBuilder email(String email) {
            this.email = email;
            return this;
        }

        public Client build() {
            Client client = new Client();
            client.setId(id);
            client.setName(name);
            client.setNumber(number);
            client.setEmail(email);
            return client;
        }
    }
}
