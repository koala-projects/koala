interface #(name.pascal.singular)Entity {
  id: #(id.type.ts);
#for(property: koalaAdmin.properties)
  #(property.name): #(property.type);
#end
}

export default #(name.pascal.singular)Entity;
