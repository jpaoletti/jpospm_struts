<%@tag description="This tag creates an standard operation header" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pm" %>
<%@attribute name = "ctx" required="true" type="org.jpos.ee.pm.core.PMContext" %>
<pm:pmtitle entity="${ctx.entity}" operation="${ctx.operation}" />
<pmfn:operations entity="${ctx.entity}" operations="${ctx.map.operations}" pmsession="${ctx.pmsession}" labels="true" />
<div id="navigation_bar"><pmfn:navigation container="${ctx.entityContainer.owner}" /></div>