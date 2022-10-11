package com.fatec.group1.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fatec.group1.model.Produto;
import com.fatec.group1.services.MantemProduto;




@Controller
@RequestMapping(path = "/grupo1")
public class GUIProdutoController {
    Logger logger = LogManager.getLogger(GUIProdutoController.class);
	@Autowired
	MantemProduto servico;

    //página de produto
    @GetMapping("/produto")
	public ModelAndView retornaFormDeCadastroDe(Produto produto) {
		ModelAndView mv = new ModelAndView("cadastrarProduto");
        List<String> lista = Arrays.asList("","Garrafa", "Camiseta", "Chinelo", "Caneca");
		mv.addObject("lista", lista);
        mv.addObject("produto", produto);
		return mv;
	}

    //cadastra o produto
    @PostMapping("/produto")
	public ModelAndView save(@Valid Produto produto, BindingResult result) {
		ModelAndView mv = new ModelAndView("listaProdutos");
		if (result.hasErrors()) {
            List<String> lista = Arrays.asList("","Garrafa", "Camiseta", "Chinelo", "Caneca");
			mv.addObject("lista", lista);
			mv.setViewName("cadastrarProduto");
		} else {
			if (servico.save(produto).isPresent()) {
				logger.info(">>>>>> controller chamou cadastrar e consultar todos");
				mv.addObject("produtos", servico.consultaTodos());
			} else {
				logger.info(">>>>>> controller cadastrar com dados invalidos");
				mv.setViewName("cadastrarProduto");
				mv.addObject("message", "Dados invalidos");
			}
		}
		return mv;
	}

    //consulta de produtos
    @GetMapping("/produtos")
	public ModelAndView retornaFormDeConsultaTodosClientes() {
		ModelAndView mv = new ModelAndView("listaProdutos");
		mv.addObject("produtos", servico.consultaTodos());
		return mv;
	}

    //view de alteração do produto
	@GetMapping("/produtos/{id}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarCliente(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("atualizarProduto");
        List<String> lista = Arrays.asList("","Garrafa", "Camiseta", "Chinelo", "Caneca");
        mv.addObject("lista", lista);
		Optional<Produto> produto = servico.consultaPorId(id);
		if (produto.isPresent()) {
			mv.addObject("produto", produto.get()); // retorna um objeto do tipo cliente
		} else {
			return new ModelAndView("paginaMenu");
		}
		return mv; // addObject adiciona objetos para view
	}

	// alteração de produto
	@PostMapping("/produtos/id/{id}")
	public ModelAndView atualizaCliente(@PathVariable("id") Long id, @Valid Produto produto, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("listaProdutos");
		logger.info(">>>>>> servico para atualizacao de dados chamado para o id => " + id);
		if (result.hasErrors()) {
			logger.info(">>>>>> servico para atualizacao de dados com erro => " + result.getFieldError().toString());
			produto.setId(id);
			ModelAndView mv = new ModelAndView("atualizarProduto");
			List<String> lista = Arrays.asList("","Garrafa", "Camiseta", "Chinelo", "Caneca");
        	mv.addObject("lista", lista);
			return mv;
		} else {
			servico.atualiza(produto);
			modelAndView.addObject("produtos", servico.consultaTodos());
		}
		return modelAndView;
	}

    // exclui produto
	@GetMapping("/produtos/id/{id}")
	public ModelAndView excluirNoFormDeConsultaCliente(@PathVariable("id") Long id) {
		servico.delete(id);
		logger.info(">>>>>> 1. servico de exclusao chamado para o id => " + id);
		ModelAndView modelAndView = new ModelAndView("listaProdutos");
		modelAndView.addObject("produtos", servico.consultaTodos());
		return modelAndView;
	}
}
