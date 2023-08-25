package dao.impl;

import org.springframework.stereotype.Repository;

import dao.PassagemDAO;
import modelo.Passagem;

@Repository
public abstract class PassagemDAOImpl extends JPADaoGenerico<Passagem, Long> implements PassagemDAO {
	public PassagemDAOImpl() {
		super(Passagem.class);
	}
}
