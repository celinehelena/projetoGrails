

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'helloWord.Usuario'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'helloWord.UsuarioPermissao'
grails.plugin.springsecurity.authority.className = 'helloWord.Permissao'
grails.plugin.springsecurity.password.algorithm = 'SHA-256'
grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"

grails.plugin.springsecurity.interceptUrlMap = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/usuario/**',  access: ['permitAll']],
	[pattern: '/cadastro/**',  access: ['permitAll']],
	[pattern: '/cadastroLojista/**',  access: ['permitAll']],
	[pattern: '/login/**',  access: ['permitAll']],
	[pattern: "/api/v1/lojista/**", access: ['ROLE_LOJISTA']],
	[pattern: '/api/v1/cliente/**',    access: ['ROLE_CLIENTE']],
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]


grails.plugin.springsecurity.filterChain.chainMap = [
 	[pattern: '/api/v1/cliente/**', filters:'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter',access: 'ROLE_CLIENTE'],
	 [pattern: '/api/v1/lojista/**', filters:'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter',access: 'ROLE_LOJISTA'],
	[pattern: '/**', filters:'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter']
]




grails.plugin.springsecurity.rest.logout.endpointUrl = '/api/logout'
grails.plugin.springsecurity.rest.login.endpointUrl = '/api/login'
grails.plugin.springsecurity.rest.login.usernamePropertyName = 'login'
grails.plugin.springsecurity.rest.login.passwordPropertyName = 'senha'
grails.plugin.springsecurity.rest.token.rendering.authoritiesPropertyName = 'permissions'
grails.plugin.springsecurity.rest.token.validation.useBearerToken = true
grails.plugin.springsecurity.rest.login.usernamePropertyName = 'login'
grails.plugin.springsecurity.rest.login.passwordPropertyName = 'senha'
grails.plugin.springsecurity.rest.token.storage.memcached.expiration = 86400
grails.plugin.springsecurity.rest.token.storage.jwt.expiration = 999999999	
grails.plugin.springsecurity.rest.login.failureStatusCode = 401

