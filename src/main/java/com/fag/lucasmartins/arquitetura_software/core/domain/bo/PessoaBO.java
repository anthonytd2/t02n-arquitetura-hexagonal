package com.fag.lucasmartins.arquitetura_software.core.domain.bo;

import com.fag.lucasmartins.arquitetura_software.core.domain.exceptions.DomainException;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public class PessoaBO {

    private UUID id;
    private String nomeCompleto;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private String telefone;

    public PessoaBO(UUID id, String nomeCompleto, String cpf, LocalDate dataNascimento, String email, String telefone) {
        this.id = id != null ? id : UUID.randomUUID();
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        
        validarIdade(dataNascimento);
        validarCpf(cpf);
        validarEmail(email);
        validarTelefone(telefone);
        
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public PessoaBO() {
    }

    private void validarIdade(LocalDate dataNascimento) {
        if (dataNascimento == null || Period.between(dataNascimento, LocalDate.now()).getYears() < 18) {
            throw new DomainException("O cliente deve ter no mínimo 18 anos.");
        }
    }

    private void validarCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            throw new DomainException("O CPF deve possuir exatamente 11 caracteres.");
        }
    }

    private void validarEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new DomainException("O e-mail deve ser válido e conter '@'.");
        }
    }

    private void validarTelefone(String telefone) {
        if (telefone == null || telefone.length() != 11) {
            throw new DomainException("O telefone deve possuir exatamente 11 caracteres.");
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        validarCpf(cpf);
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        validarIdade(dataNascimento);
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validarEmail(email);
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        validarTelefone(telefone);
        this.telefone = telefone;
    }
}