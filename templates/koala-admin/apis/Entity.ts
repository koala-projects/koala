export default interface #(name.pascal.singular)Entity {
#for(property: properties)
  #(property.name.camel.singular): #(property.type.ts)
#end
}
