import { BasicColumn, FormSchema } from '/@/components/Table';

export const columns: BasicColumn[] = [
#for(property: koalaAdmin.properties)
  {
    title: '#(property.description)',
    dataIndex: '#(property.name)',
  },
#end
];

export const searchFormSchema: FormSchema[] = [
#for(property: koalaAdmin.properties)
  {
    field: '#(property.name)',
    label: '#(property.description)',
    component: '#(property.component)',
    colProps: {
  	  xl: 12,
  	  xxl: 8,
    },
  },
#end
];

export const formSchema: FormSchema[] = [
#for(property: koalaAdmin.properties)
  {
    field: '#(property.name)',
    label: '#(property.description)',
    component: '#(property.component)',
  },
#end
];
