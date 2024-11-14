export default function useGetImageUrl(imageFile = "", imageType = "book") {
  const config = useRuntimeConfig();

  if (imageFile === null || imageFile === "" || imageFile === undefined) {
    return `/png/no-tree-mid-book.png`; // TODO 디폴트 이미지 처리를 해줘야 함
  }

  if (imageFile.substring(0, 4) === "http") {
    return imageFile;
  } else {
    return `${config.public.apiBase}/images/${imageType}/${imageFile}`;
  }
}
