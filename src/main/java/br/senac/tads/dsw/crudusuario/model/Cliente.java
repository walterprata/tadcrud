package br.senac.tads.dsw.crudusuario.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


import br.senac.tads.dsw.crudusuario.enums.StatusCliente;

@Entity
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    
    private String userName;
    private String nome;
    private String senha;
    
    @Enumerated(EnumType.STRING)
    private StatusCliente statusCliente;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "CLIENTE_PAPEL",
            joinColumns = @JoinColumn(name="ID_CLIENTE"),
            inverseJoinColumns = @JoinColumn(name="ID_PAPEL"))
    private List<Papel> papeis;
    
    
    private String dataCadastro;
    
    private transient List<Integer> idsPapeis;

    public Cliente() {
    }

	public Cliente(Integer id, String userName, String nome, String senha, StatusCliente statusCliente,
			String dataCadastro) {
		super();
		this.id = id;
		this.userName = userName;
		this.nome = nome;
		this.senha = senha;
		this.statusCliente = statusCliente;
		this.dataCadastro = dataCadastro;
		
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public StatusCliente getStatusCliente() {
		return statusCliente;
	}

	public void setStatusCliente(StatusCliente statusCliente) {
		this.statusCliente = statusCliente;
	}

	public List<Papel> getPapeis() {
        return papeis;
    }

    public void setPapeis(List<Papel> papeis) {
        this.papeis = papeis;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    public List<Integer> getIdsPapeis() {
        return idsPapeis;
    }

    public void setIdsCategorias(List<Integer> idsPapeis) {
        this.idsPapeis = idsPapeis;
    }
    
	@Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
