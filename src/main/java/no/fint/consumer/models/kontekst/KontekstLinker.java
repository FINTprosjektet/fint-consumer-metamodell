package no.fint.consumer.models.kontekst;

import no.fint.model.resource.Link;
import no.fint.model.resource.metamodell.KontekstResource;
import no.fint.model.resource.metamodell.KontekstResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;


@Component
public class KontekstLinker extends FintLinker<KontekstResource> {

    public KontekstLinker() {
        super(KontekstResource.class);
    }

    public void mapLinks(KontekstResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public KontekstResources toResources(Collection<KontekstResource> collection) {
        KontekstResources resources = new KontekstResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(KontekstResource kontekst) {
        if (!isNull(kontekst.getId()) && !isEmpty(kontekst.getId().getIdentifikatorverdi())) {
            return createHrefWithId(kontekst.getId().getIdentifikatorverdi(), "id");
        }
        
        return null;
    }
    
}
