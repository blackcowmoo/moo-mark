import { exec } from '@/utils/exec';

const kubectl = async (command: string): Promise<string> => exec(`kubectl ${command}`);

export const getPod = async (pod: string): Promise<string> => {
  const podRegExp = new RegExp(`^${pod}`);
  const { items } = JSON.parse(await kubectl('get pods -o json'));

  return items.map((item: any) => item.metadata.name).find((item: string) => podRegExp.test(item));
};

interface Log {
  timestamp: string;
  text: string;
}

export const getLog = async (name: string): Promise<Log[]> => {
  const logs = await kubectl(`logs --timestamps=true --tail=4000 ${name}`);
  return logs
    .split('\n')
    .map((log) => {
      const [timestamp, ...texts] = log.split(' ');
      return { timestamp, text: texts.join(' ') };
    })
    .filter(({ timestamp }) => timestamp);
};
