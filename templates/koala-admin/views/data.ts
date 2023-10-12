import { BasicColumn, FormSchema } from '/@/components/Table';

export const columns: BasicColumn[] = [
#for(property: properties)
  {
    title: '#(property.description)',
    dataIndex: '#(property.name.camel.singular)',
  },
#end
];

export const searchFormSchema: FormSchema[] = [
#for(property: properties)
  {
    field: '#(property.name.camel.singular)',
    label: '#(property.description)',
    component: '#(property.type.vben)',
    colProps: {
  	  xl: 12,
  	  xxl: 8,
    },
  },
#end
];

export const formSchema: FormSchema[] = [
#for(property: properties)
  {
    field: '#(property.name.camel.singular)',
    label: '#(property.description)',
    component: '#(property.type.vben)',
  },
#end
];
